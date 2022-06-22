package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
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
	private final AppUserService appUserService = AppUserServiceImpl.getInstance();
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
						getUsers(response);
						break;
					case "profile":
						getProfile(request, response);
						break;
					case "verify":
						if (pathParts.length == 3) {
							try {
								UUID token = UUID.fromString(pathParts[2]);
								verify(request, response, token);
							} catch (Exception e) {
								request.setAttribute("error", "Mã xác nhận không hợp lệ");
								request.getRequestDispatcher("/user/verify/failure").forward(request, response);
							}
						} else {
							response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						}
						break;
				}
				break;
			case "POST":
				switch (pathParts[1]) {
					case "register":
						register(request, response);
						break;
					case "resend-verify-email":
						resendVerifyEmail(request, response);
						break;
					case "reset-password":
						resetPassword(request, response);
						break;
					case "login":
						login(request, response);
						break;
				}
				break;
		}
	}

	public void getUsers(HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		AppServiceResult<List<AppUserForAdminDto>> result = appUserService.getUsers();
		if (result.isSuccess()) {
			response.setStatus(200);
			response.getWriter().println(GSON.toJson(result));
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String phone = request.getParameter("phone");
		Boolean isMale = request.getParameter("gender").equals("1");
		String email = request.getParameter("email");
		String username = request.getParameter("usernameSignup");
		String password = request.getParameter("passwordSignup");

		UserRegister userRegister = new UserRegister(lastName, firstName, phone, isMale, email, username, password);

		AppBaseResult result = appUserService.register(userRegister);

		response.getWriter().println(GSON.toJson(result));
	}

	private void resendVerifyEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");

		AppBaseResult result = appUserService.resendVerifyEmail(email);

		response.getWriter().println(GSON.toJson(result));
	}

	private void verify(HttpServletRequest request, HttpServletResponse response, UUID token) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		AppBaseResult result = appUserService.verifyEmail(token);

		if (result.isSuccess()) {
			request.getRequestDispatcher("/user/verify/success").forward(request, response);
		} else {
			request.setAttribute("error", result.getMessage());
			request.getRequestDispatcher("/user/verify/failure").forward(request, response);
		}
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("usernameSignin");
		String password = request.getParameter("passwordSignin");

		AppServiceResult<AppUser> result = appUserService.getUserLogin(new UserLogin(username, password));

		if (result.isSuccess()) {
			AppUserDomain appUserDomain = new AppUserDomain(result.getData());
			String userToken = appJwtTokenProvider.generateJwtToken(appUserDomain);
			UserLoginResponse userLoginResponse = new UserLoginResponse(appUserDomain.getUserId(), appUserDomain.getUsername(), userToken);

			// save to session
			HttpSession session = request.getSession();
			session.setAttribute("user", userLoginResponse);

			AppServiceResult<UserLoginResponse> res =
					new AppServiceResult<>(true, 0, "Success", userLoginResponse);

			response.getWriter().println(GSON.toJson(res));
		} else {
			response.getWriter().println(GSON.toJson(result));
		}
	}

	public void getProfile(HttpServletRequest request, HttpServletResponse  response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			AppServiceResult<UserInfoDtoResponse> result = appUserService.getProfile(id);

			response.getWriter().println(GSON.toJson(result));
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	public void saveProfile(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			Long id = Long.parseLong(request.getParameter("id"));
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");

			AppBaseResult result = appUserService.changePassword(new ChangePassword(id, oldPassword, newPassword));

			response.getWriter().println(GSON.toJson(result));
		} catch (Exception e) {
			AppBaseResult result = new AppBaseResult(false, AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
			response.getWriter().println(GSON.toJson(result));
		}
	}

	public void uploadAvatar(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");

		AppBaseResult result = appUserService.resetPassword(email);

		response.getWriter().println(GSON.toJson(result));
	}

	public void updateStatus(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}