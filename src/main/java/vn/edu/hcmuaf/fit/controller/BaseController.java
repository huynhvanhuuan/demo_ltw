package vn.edu.hcmuaf.fit.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "base", value = "/api/*")
public class BaseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/product":
                request.getRequestDispatcher("/api/product").forward(request, response);
                break;
            default:
                response.sendError(404);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
