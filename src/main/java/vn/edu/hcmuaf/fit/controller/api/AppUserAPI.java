package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.appuser.AppUserForAdminDto;
import vn.edu.hcmuaf.fit.dto.appuser.UserRegister;
import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.service.impl.AppUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-user", urlPatterns = "/api/user/*")
@MultipartConfig
public class AppUserAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().serializeNulls().create();
	private final AppUserService userService = AppUserServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			AppServiceResult<List<AppUserForAdminDto>> result = userService.getUsers();
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().println(GSON.toJson(result));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		} else {
			try {
				Long id = Long.parseLong(pathInfo.substring(1));
				AppServiceResult<AppUserForAdminDto> result = userService.getUser(id);
				if (result.isSuccess()) {
					response.setStatus(200);
					response.getWriter().println(GSON.toJson(result));
				} else {
					response.sendError(result.getErrorCode(), result.getMessage());
				}
			} catch (NumberFormatException e) {
				response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getPathInfo()) {
			case "/register":
				register(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			default:
				doGet(request, response);
				break;
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

		AppBaseResult result = userService.register(userRegister);

		response.getWriter().println(GSON.toJson(result));
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

	}
}
