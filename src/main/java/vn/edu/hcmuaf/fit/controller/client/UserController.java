package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.service.impl.AppUserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "client-user", urlPatterns = "/user/*")
public class UserController extends HttpServlet {
    private final AppUserService userService = AppUserServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/account/profile":
                doGetProfile(request, response);
                break;
            case "/account/payment":
                doGetPayment(request, response);
                break;
            case "/account/address":
                doGetAddress(request, response);
                break;
            case "/account/password":
                doGetPassword(request, response);
                break;
            case "/purchase":
                doGetPurchase(request, response);
                break;
            default:
                request.getRequestDispatcher("/views/client/user/profile.jsp").forward(request, response);
                break;
        }
    }

    private void doGetProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/account/profile.jsp").forward(request, response);
    }

    private void doGetPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/account/payment.jsp").forward(request, response);
    }

    private void doGetAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/account/address.jsp").forward(request, response);
    }

    private void doGetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/account/password.jsp").forward(request, response);
    }

    private void doGetPurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/purchase.jsp").forward(request, response);
    }
}
