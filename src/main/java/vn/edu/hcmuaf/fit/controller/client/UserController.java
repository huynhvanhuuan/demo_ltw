package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.service.UserService;
import vn.edu.hcmuaf.fit.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "client-user", urlPatterns = "/user/*")
public class UserController extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/client/user/dashboard.jsp").forward(request, response);
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

    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
