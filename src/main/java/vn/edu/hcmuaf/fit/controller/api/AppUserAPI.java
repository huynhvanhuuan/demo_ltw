package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.domain.*;
import vn.edu.hcmuaf.fit.dto.appuser.*;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.infrastructure.AppJwtTokenProvider;
import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.service.impl.AppUserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "api-user", urlPatterns = "/api/user/*")
@MultipartConfig
public class AppUserAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().serializeNulls().create();
	private final AppUserService appUserService = new AppUserServiceImpl();
	private final AppJwtTokenProvider appJwtTokenProvider = new AppJwtTokenProvider();

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String path = request.getPathInfo();
		if (path == null || path.equals("/")) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String[] pathParts = path.split("/");
		String method = request.getMethod();
		switch (method) {
			case "GET":
				switch (pathParts[1]) {
					case "list":
						getUsers(request);
						break;
					case "account":
						switch (pathParts[2]) {
							case "profile":
								getProfile(request);
								break;
						}
						break;
					case "verify":
						UUID token = UUID.fromString(pathParts[2]);
						verify(request, token);
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
						resetPassword(request, response);
						break;
					case "login":
						login(request);
						break;
					case "change-password":
						changePassword(request);
						break;
				}
				break;
		}
	}

	public void getUsers(HttpServletRequest request) {
		AppServiceResult<List<AppUserForAdminDto>> result = appUserService.getUsers();
		request.setAttribute("result", GSON.toJson(result));
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
		request.setAttribute("result", GSON.toJson(result));
	}

	private void resendVerifyEmail(HttpServletRequest request) {
		String email = request.getParameter("email");
		AppBaseResult result = appUserService.resendVerifyEmail(email);
		request.setAttribute("result", GSON.toJson(result));
	}

	private void verify(HttpServletRequest request, UUID token) {
		AppBaseResult result = appUserService.verifyEmail(token);
		request.setAttribute("result", GSON.toJson(result));
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
		request.setAttribute("result", GSON.toJson(res));
	}

	public void getProfile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("user_id");
		AppServiceResult<UserInfoDtoResponse> result = appUserService.getProfile(userId);
		request.setAttribute("result", GSON.toJson(result));
	}

	public void saveProfile(HttpServletRequest request, HttpServletResponse response) {
	}

	public void changePassword(HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("user_id");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");

		AppBaseResult result = appUserService.changePassword(new ChangePassword(id, currentPassword, newPassword));
		request.setAttribute("result", GSON.toJson(result));
	}

	public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) {
	}

	public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		AppBaseResult result = appUserService.resetPassword(email);
		request.setAttribute("result", GSON.toJson(result));
	}

	public void updateStatus(HttpServletRequest request, HttpServletResponse response) {
	}
}
