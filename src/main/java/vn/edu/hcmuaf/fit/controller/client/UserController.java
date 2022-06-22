package vn.edu.hcmuaf.fit.controller.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "client-user", urlPatterns = "/user/*")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String[] pathParts = path.split("/");
        switch (pathParts[1]) {
            case "register":
                switch (pathParts[2]) {
                    case "success":
                        getRegisterSuccess(request, response);
                        break;
                    case "failure":
                        getRegisterFailure(request, response);
                        break;
                    default:
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        break;
                }
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
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        break;
                }
                break;
            case "logout":
                if (request.getSession().getAttribute("user") != null) {
                    request.logout();
                    request.getSession().invalidate();
                    response.sendRedirect("/home");
                }
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
                        getProfile(request, response);
                        break;
                    case "payment":
                        getPayment(request, response);
                        break;
                    case "address":
                        getAddress(request, response);
                        break;
                    case "change-password":
                        getChangePassword(request, response);
                        break;
                    default:
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        break;
                }
                break;
            case "purchase":
                getPurchase(request, response);
                break;
            default:
                request.getRequestDispatcher("/view/client/user/profile.jsp").forward(request, response);
                break;
        }
    }

    private void getRegisterSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/register/success.jsp").forward(request, response);
    }

    private void getRegisterFailure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        request.getRequestDispatcher("/view/client/user/register/failure.jsp").forward(request, response);
    }

    private void getVerifySuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/verify/success.jsp").forward(request, response);
    }

    private void getVerifyFailure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        request.getRequestDispatcher("/view/client/user/verify/failure.jsp").forward(request, response);
    }

    private void getForgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/forgot-password.jsp").forward(request, response);
    }

    private void getResetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/reset-password/success.jsp").forward(request, response);
    }

    private void getProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/account/profile.jsp").forward(request, response);
    }

    private void getPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/account/payment.jsp").forward(request, response);
    }

    private void getAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/client/user/account/address.jsp").forward(request, response);
    }

    private void getChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/account/password.jsp").forward(request, response);
    }

    private void getPurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/purchase.jsp").forward(request, response);
    }
}
