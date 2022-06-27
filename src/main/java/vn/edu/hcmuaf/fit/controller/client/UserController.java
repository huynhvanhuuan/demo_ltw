package vn.edu.hcmuaf.fit.controller.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.AddressDto;
import vn.edu.hcmuaf.fit.dto.appuser.UserLoginResponse;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.service.AddressService;
import vn.edu.hcmuaf.fit.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.UUID;

import static vn.edu.hcmuaf.fit.constant.FileConstant.TEMP_PROFILE_IMAGE_BASE_URL;
import static vn.edu.hcmuaf.fit.constant.FileConstant.USER_IMAGE_PATH;

@WebServlet(name = "client-user", urlPatterns = "/user/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 100) // 100MB
public class UserController extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private AddressService addressService;

    @Override
    public void init() throws ServletException {
        addressService = new AddressServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            request.getRequestDispatcher("/user/account/profile").forward(request, response);
            return;
        }

        String[] pathParts = path.split("/");
        String method = request.getMethod();
        switch (method) {
            case "GET":
                switch (pathParts[1]) {
                    case "register":
                        if ("success".equals(pathParts[2])) getRegisterSuccess(request, response);
                        else response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        break;
                    case "verify":
                        switch (pathParts[2]) {
                            case "success":
                                getVerifySuccess(request, response);
                                break;
                            case "failure":
                                getVerifyFailure(request, response);
                                break;
                            default:
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
                    case "logout":
                        request.logout();
                        request.getSession().invalidate();
                        response.sendRedirect("/home");
                        break;
                    case "forgot-password":
                        getForgotPassword(request, response);
                        break;
                    case "reset-password":
                        getResetPassword(request, response);
                        break;
                    case "account":
                        switch (pathParts[2]) {
                            case "profile":
                                request.setAttribute("path", "profile");
                                getProfile(request, response);
                                break;
                            case "payment":
                                request.setAttribute("path", "payment");
                                getPayment(request, response);
                                break;
                            case "address":
                                request.setAttribute("path", "address");
                                getAddress(request, response);
                                break;
                            case "change-password":
                                request.setAttribute("path", "change-password");
                                getChangePassword(request, response);
                                break;
                        }
                        break;
                    case "purchase":
                        request.setAttribute("path", "purchase");
                        getPurchase(request, response);
                        break;
                    case "wishlist":
                        getWishlist(request, response);
                        break;
//                    default:
//                        response.sendRedirect("/user/purchase");
//                        break;
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
                    case "upload-profile-image":
                        uploadImage(request, response);
                        break;
                }
                break;
            case "PUT":
                switch (pathParts[1]) {
                    case "profile":
                        saveProfile(request, response);
                        break;
                    case "change-password":
                        changePassword(request, response);
                        break;
                }
                break;
        }
    }

    /* GET METHOD */
    public void getRegisterSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/register/success.jsp").forward(request, response);
    }

    public void getVerifySuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/verify/success.jsp").forward(request, response);
    }

    public void getVerifyFailure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/verify/failure.jsp").forward(request, response);
    }

    public void getForgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/forgot-password.jsp").forward(request, response);
    }

    public void getResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/reset-password/success.jsp").forward(request, response);
    }

    public void getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        getPrivateProfile(request, response);

        request.getRequestDispatcher("/view/client/user/account/main.jsp").forward(request, response);
    }

    private void getPrivateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew() || session.getAttribute("user") == null) {
            request.getRequestDispatcher("/api/user/account/profile").include(request, response);
            Type type = new TypeToken<AppServiceResult<UserInfoDtoResponse>>() {}.getType();
            AppServiceResult<UserInfoDtoResponse> result = new Gson().fromJson((String) request.getAttribute("profile"), type);

            if (result.isSuccess()) {
                result.getData().setImageUrl(
                        result.getData().getImageUrl().contains(TEMP_PROFILE_IMAGE_BASE_URL) ?
                                result.getData().getImageUrl() : USER_IMAGE_PATH + result.getData().getImageUrl());
                session.setAttribute("user", result.getData());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            request.removeAttribute("profile");
        }
    }

    public void getPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/account/main.jsp").forward(request, response);
    }

    public void getAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");

        AppServiceResult<List<AddressDto>> result = addressService.getAddressByUserId(userId);
        if (result.isSuccess()) {
            request.setAttribute("addresses", result.getData());
            request.getRequestDispatcher("/view/client/user/account/main.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void getChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/account/main.jsp").forward(request, response);
    }

    public void getPurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        getPrivateProfile(request, response);

        request.getRequestDispatcher("/api/user/purchase").include(request, response);

        request.removeAttribute("purchase");
        request.getRequestDispatcher("/view/client/user/account/main.jsp").forward(request, response);
    }

    public void getWishlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/wishlist.jsp").forward(request, response);
    }

    /* POST METHOD */
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/register").include(request, response);

        Type type = new TypeToken<AppBaseResult>() {}.getType();
        AppBaseResult result = GSON.fromJson((String) request.getAttribute("register"), type);

        request.removeAttribute("register");
        response.getWriter().println(GSON.toJson(result));
    }

    private void resendVerifyEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/resend-verify-email").include(request, response);

        Type type = new TypeToken<AppBaseResult>() {}.getType();
        AppBaseResult result = GSON.fromJson((String) request.getAttribute("resendVerify"), type);

        request.removeAttribute("resendVerify");
        response.getWriter().println(GSON.toJson(result));
    }

    private void verify(HttpServletRequest request, HttpServletResponse response, UUID token) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/verify/" + token).include(request, response);

        Type type = new TypeToken<AppBaseResult>() {}.getType();
        AppBaseResult result = GSON.fromJson((String) request.getAttribute("verify"), type);

        request.removeAttribute("verify");
        if (result.isSuccess()) {
            request.getRequestDispatcher("/user/verify/success").forward(request, response);
        } else {
            request.setAttribute("error", result.getMessage());
            request.getRequestDispatcher("/user/verify/failure").forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/login").include(request, response);
        Type type = new TypeToken<AppServiceResult<UserLoginResponse>>() {}.getType();
        AppServiceResult<UserLoginResponse> result = GSON.fromJson((String) request.getAttribute("login"), type);

        UserLoginResponse userLoginResponse = result.getData();
        if (result.isSuccess()) {
            response.setHeader("Authorization", "Bearer " + userLoginResponse.getToken());

            HttpSession session = request.getSession();
            session.setAttribute("user_id", userLoginResponse.getUserId());
            session.setAttribute("username", userLoginResponse.getUsername());
            session.setAttribute("token", userLoginResponse.getToken());
        }

        request.removeAttribute("login");
        response.getWriter().println(GSON.toJson(result));
    }

    public void saveProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/profile").include(request, response);

        Type type = new TypeToken<AppServiceResult<UserInfoDtoResponse>>() {}.getType();
        AppServiceResult<UserInfoDtoResponse> result = GSON.fromJson((String) request.getAttribute("profile"), type);

        if (result.isSuccess()) {
            result.getData().setImageUrl(
                    result.getData().getImageUrl().contains(TEMP_PROFILE_IMAGE_BASE_URL) ?
                            result.getData().getImageUrl() : USER_IMAGE_PATH + result.getData().getImageUrl());
            request.getSession().setAttribute("user", result.getData());
        }

        request.removeAttribute("profile");
        response.getWriter().println(GSON.toJson(result));
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/change-password").include(request, response);

        Type type = new TypeToken<AppBaseResult>() {}.getType();
        AppBaseResult result = GSON.fromJson((String) request.getAttribute("changePassword"), type);

        request.removeAttribute("changePassword");
        response.getWriter().println(GSON.toJson(result));
    }

    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/upload-profile-image").include(request, response);

        Type type = new TypeToken<AppServiceResult<UserInfoDtoResponse>>() {}.getType();
        AppServiceResult<UserInfoDtoResponse> result = GSON.fromJson((String) request.getAttribute("uploadImage"), type);

        if (result.isSuccess()) {
            result.getData().setImageUrl(
                    result.getData().getImageUrl().contains(TEMP_PROFILE_IMAGE_BASE_URL) ?
                            result.getData().getImageUrl() : USER_IMAGE_PATH + result.getData().getImageUrl());
            request.getSession().setAttribute("user", result.getData());
        }

        request.removeAttribute("uploadImage");
        response.getWriter().println(GSON.toJson(result));
    }

    public void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/api/user/reset-password").include(request, response);

        Type type = new TypeToken<AppBaseResult>() {}.getType();
        AppBaseResult result = GSON.fromJson((String) request.getAttribute("result"), type);

        request.removeAttribute("result");
        response.getWriter().println(GSON.toJson(result));
    }
}
