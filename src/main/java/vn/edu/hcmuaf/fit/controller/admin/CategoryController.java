package vn.edu.hcmuaf.fit.controller.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.service.CategoryService;
import vn.edu.hcmuaf.fit.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

@WebServlet(name = "admin-category", value = "/admin/category")
public class CategoryController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "CATEGORY MANAGEMENT");
		request.getRequestDispatcher("/view/admin/category.jsp").forward(request, response);
	}
	
	private void changeActive(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String sku = request.getParameter("sku");
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}
	
	private void checkExist(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		response.setContentType("application/json");
		String sku = request.getParameter("sku");
		String name = request.getParameter("name");
		PrintWriter out = response.getWriter();
		out.close();
	}
	
	private void getListSkuHasProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.close();
	}
}
