package vn.edu.hcmuaf.fit.controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "admin-category", urlPatterns = "/admin/category/*")
@MultipartConfig
public class CategoryController extends HttpServlet {
	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] pathParts = request.getPathInfo() != null ? request.getPathInfo().split("/") : new String[0];
		
		String method = request.getMethod();
		switch (method) {
			case "GET":
				if ("list".equals(pathParts[1])) getCategories(request, response);
					else getCategory(request, response, pathParts[1]);
				break;
			case "POST":
				createCategory(request, response);
				break;
			case "PUT":
				switch (pathParts[1]) {
					case "update-category":
						updateCategory(request, response);
						break;
					case "update-status":
						updateStatus(request, response);
						break;
				}
				break;
			case "DELETE":
				deleteCategory(request, response);
				break;
		}
	}

	private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/api/category/update-status").forward(request, response);
	}

	private void getCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "QUẢN LÝ DANH MỤC");
		request.getRequestDispatcher("/view/admin/category.jsp").forward(request, response);
	}

	private void getCategory(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
		request.getRequestDispatcher("/api/category/" + id).forward(request, response);
	}

	private void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/api/category").forward(request, response);
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/api/category/update-category").forward(request, response);
	}

	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/api/category").forward(request, response);
	}
}
