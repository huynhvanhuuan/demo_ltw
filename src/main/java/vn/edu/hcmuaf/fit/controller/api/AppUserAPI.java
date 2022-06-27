package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.*;
import vn.edu.hcmuaf.fit.dto.appuser.*;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoRequest;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.infrastructure.AppJwtTokenProvider;
import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.service.impl.AppUserServiceImpl;
import vn.edu.hcmuaf.fit.util.AppUtils;
import vn.edu.hcmuaf.fit.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static vn.edu.hcmuaf.fit.constant.FileConstant.*;

@WebServlet(name = "api-user", urlPatterns = "/api/user/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 100) // 100MB
public class AppUserAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().serializeNulls().create();
	private AppUserService appUserService;
	private AppJwtTokenProvider appJwtTokenProvider;

	@Override
	public void init() throws ServletException {
		appUserService = new AppUserServiceImpl();
		appJwtTokenProvider = new AppJwtTokenProvider();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		if (path == null || path.equals("/")) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String[] pathParts = path.split("/"); // []/account/profile
		String method = request.getMethod();
		switch (method) {
			case "GET":
				switch (pathParts[1]) {
					case "list":
						getUsers(request);
						break;
					case "verify":
						UUID token = UUID.fromString(pathParts[2]);
						verify(request, token);
						break;
					case "account":
						switch (pathParts[2]) {
							case "profile":
								getProfile(request);
								break;
							default:
								response.setStatus(HttpServletResponse.SC_NOT_FOUND);
								break;
						}
						break;
					case "purchase":
						getPurchase(request);
					default:
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						break;
				}
				break;
			case "POST":
				switch (pathParts[1]) {
					case "register":
						register(request);
						break;
					case "resend-verify-email":
						resendVerifyEmail(request);
						break;
					case "reset-password":
						resetPassword(request);
						break;
					case "login":
						login(request);
						break;
					case "upload-profile-image":
						uploadImage(request);
						break;
					case "add-address":
						addAddress(request);
						break;
					default:
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						break;
				}
				break;
			case "PUT":
				switch (pathParts[1]) {
					case "profile":
						saveProfile(request);
						break;
					case "change-password":
						changePassword(request);
						break;
					default:
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						break;
				}
				break;
		}
	}

	public void getUsers(HttpServletRequest request) {
		AppServiceResult<List<AppUserForAdminDto>> result = appUserService.getUsers();
		request.setAttribute("list", GSON.toJson(result));
	}

	public void register(HttpServletRequest request) throws ServletException, IOException {
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String phone = request.getParameter("phone");
		Boolean isMale = request.getParameter("gender").equals("1");
		String email = request.getParameter("email");
		String username = request.getParameter("usernameSignup");
		String password = request.getParameter("passwordSignup");

		UserRegister userRegister = new UserRegister(lastName, firstName, phone, isMale, email, username, password);
		AppBaseResult result = appUserService.register(userRegister);
		request.setAttribute("register", GSON.toJson(result));
	}

	private void resendVerifyEmail(HttpServletRequest request) {
		String email = request.getParameter("email");
		AppBaseResult result = appUserService.resendVerifyEmail(email);
		request.setAttribute("resendVerify", GSON.toJson(result));
	}

	private void verify(HttpServletRequest request, UUID token) {
		AppBaseResult result = appUserService.verifyEmail(token);
		request.setAttribute("verify", GSON.toJson(result));
	}

	public void login(HttpServletRequest request) throws ServletException, IOException {
		String username = request.getParameter("usernameSignin");
		String password = request.getParameter("passwordSignin");

		AppServiceResult<AppUser> result = appUserService.getUserLogin(new UserLogin(username, password));
		UserLoginResponse userLoginResponse = null;
		if (result.isSuccess()) {
			AppUserDomain appUserDomain = new AppUserDomain(result.getData());
			String userToken = appJwtTokenProvider.generateJwtToken(appUserDomain);

			userLoginResponse = new UserLoginResponse(appUserDomain.getUserId(), appUserDomain.getUsername(), userToken);
		}
		AppServiceResult<UserLoginResponse> res = new AppServiceResult<>(result.isSuccess(), result.getErrorCode(), result.getMessage(), userLoginResponse);
		request.setAttribute("login", GSON.toJson(res));
	}

	public void getProfile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("user_id");

		AppServiceResult<UserInfoDtoResponse> result = appUserService.getProfile(userId);
		request.setAttribute("profile", GSON.toJson(result));
	}

	private void getPurchase(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.isNew() || session.getAttribute("user") == null) {
			getProfile(request);
		}

		request.setAttribute("purchase", null);
	}

	public void saveProfile(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			Long userId = (Long) session.getAttribute("user_id");

			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			boolean isMale = request.getParameter("gender").equals("1");
			String dateOfBirth = request.getParameter("dateOfBirth");

			UserInfoDtoRequest userInfo = new UserInfoDtoRequest();
			userInfo.setUserId(userId);
			userInfo.setLastName(lastName);
			userInfo.setFirstName(firstName);
			userInfo.setFullName(AppUtils.getFullName(lastName, firstName));
			userInfo.setMale(isMale);
			userInfo.setDateOfBirth(DateUtil.toDate(dateOfBirth));

			AppServiceResult<UserInfoDtoResponse> result = appUserService.saveProfile(userInfo);
			request.setAttribute("profile", GSON.toJson(result));
		} catch (Exception e) {
			AppServiceResult<UserInfoDtoResponse> result = new AppServiceResult<>(false, AppError.Validation.errorCode(), "Không thể cập nhật", null);
			request.setAttribute("profile", GSON.toJson(result));
		}
	}

	public void changePassword(HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("user_id");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");

		AppBaseResult result = appUserService.changePassword(new ChangePassword(id, currentPassword, newPassword));
		request.setAttribute("changePassword", GSON.toJson(result));
	}

	public void uploadImage(HttpServletRequest request) {
		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(request)) {
			// if not, we stop here
			AppServiceResult<UserInfoDtoResponse> result = new AppServiceResult<>(false, AppError.Validation.errorCode(), "Form must has enctype=multipart/form-data.", null);
			request.setAttribute("uploadImage", GSON.toJson(result));
			return;
		}
		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(FILE_SIZE_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		try {
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {
					// processes only fields that are not form fields
					if (!item.isFormField() && item.getFieldName().equals("image")) {
						HttpSession session = request.getSession();
						Long userId = (Long) session.getAttribute("user_id");

						AppServiceResult<UserInfoDtoResponse> result = appUserService.uploadImage(item, userId);
						request.setAttribute("uploadImage", GSON.toJson(result));
					}
				}
			}
		} catch (Exception e) {
			AppServiceResult<UserInfoDtoResponse> result = new AppServiceResult<>(false, AppError.Validation.errorCode(), "Không thể tải ảnh lên!", null);
			request.setAttribute("uploadImage", GSON.toJson(result));
		}
	}

	public void resetPassword(HttpServletRequest request) {
		String email = request.getParameter("email");
		AppBaseResult result = appUserService.resetPassword(email);
		request.setAttribute("resetPassword", GSON.toJson(result));
	}

	private void addAddress(HttpServletRequest request) {

	}

	public void updateStatus(HttpServletRequest request, HttpServletResponse response) {
	}
}
