package vn.edu.hcmuaf.fit.controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin-trademark", value = "/admin/trademark")
public class TrademarkController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "QUẢN LÝ THƯƠNG HIỆU");
        request.getRequestDispatcher("/view/admin/trademark.jsp").forward(request, response);
    }
}
