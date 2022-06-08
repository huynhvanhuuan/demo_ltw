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
	private static final Gson GSON = new GsonBuilder().create();
	private CategoryService categoryService;
	
	@Override
	public void init() throws ServletException {
		categoryService = new CategoryServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		if ("/list".equals(action)) {
			AppServiceResult<List<CategoryDto>> result = categoryService.getCategories();
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().println(GSON.toJson(result.getData()));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		} else {
			long id = Long.parseLong(action.substring(1));
			
			AppServiceResult<CategoryDto> result = categoryService.getCategory(id);
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().write(GSON.toJson(result.getData()));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		for (CategoryDto categoryDto : categoryService.getCategories().getData()) {
			PrintWriter out = response.getWriter();
			out.println(categoryDto.getName());
		}
		response.getWriter().close();
		request.setAttribute("title", "CATEGORY MANAGEMENT");
		request.getRequestDispatcher("/view/admin/category.jsp").forward(request, response);
	}
	
	private void getMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setAttribute("title", "QUẢN LÝ THỂ LOẠI");
		request.getRequestDispatcher("/view/admin/category.jsp").forward(request, response);
	}
	
	private void get(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("application/json");
		String sku = request.getParameter("sku");
		PrintWriter out = response.getWriter();
		out.close();
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String sku = request.getParameter("sku");
		String name = request.getParameter("name");
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		String oldSku = request.getParameter("old_sku");
		String newSku = request.getParameter("sku");
		String name = request.getParameter("name");
		String active = request.getParameter("active");
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		response.setContentType("application/json");
		String skus = request.getParameter("skus");
		StringTokenizer st = new StringTokenizer(skus, "[\",]");
		while (st.hasMoreTokens()) {
			String sku = st.nextToken();
		}
		PrintWriter pw = response.getWriter();
		pw.close();
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
