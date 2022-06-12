package vn.edu.hcmuaf.fit.controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "admin-category", value = "/admin/category")
public class CategoryController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "QUẢN LÝ DANH MỤC");
		request.getRequestDispatcher("/view/admin/category.jsp").forward(request, response);
	}
}
