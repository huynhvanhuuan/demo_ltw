package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.category.*;
import vn.edu.hcmuaf.fit.service.CategoryService;
import vn.edu.hcmuaf.fit.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(name = "api-category", urlPatterns = "/api/category/*")
@MultipartConfig
public class CategoryAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().serializeNulls().create();
	private CategoryService categoryService;

	@Override
	public void init() throws ServletException {
		categoryService = new CategoryServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String[] pathParts = request.getPathInfo() != null ? request.getPathInfo().split("/") : new String[0];
		String method = request.getMethod();
		switch (method) {
			case "GET":
				if ("list".equals(pathParts[1])) getCategories(response);
					else getCategory(response, pathParts[1]);
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

	private void getCategories(HttpServletResponse response) throws IOException {
		AppServiceResult<List<CategoryDto>> result = categoryService.getCategories();
		response.getWriter().write(GSON.toJson(result));
	}

	private void getCategory(HttpServletResponse response, String id) throws IOException {
		try {
			AppServiceResult<CategoryDto> result = categoryService.getCategory(Long.parseLong(id));
			response.getWriter().println(GSON.toJson(result));
		} catch (NumberFormatException e) {
			AppServiceResult<CategoryDto> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Mã thể loại không hợp lệ: " + id, null);
			response.getWriter().write(GSON.toJson(result));
		}
	}
	
	private void createCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String sku = request.getParameter("sku");
		
		CategoryCreate newCategory = new CategoryCreate(sku, name);
		
		AppBaseResult result = categoryService.createCategory(newCategory);
		response.getWriter().println(GSON.toJson(result));
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			String name = request.getParameter("name");
			String sku = request.getParameter("sku");
//			String json = StringUtil.getStringFromInputStream(request.getInputStream());
//			Type type = new TypeToken<CategoryUpdate>() {}.getType();
//			CategoryUpdate updateCategory = GSON.fromJson(json, type);
			CategoryUpdate updateCategory = new CategoryUpdate(id, sku, name);
			AppBaseResult result = categoryService.updateCategory(updateCategory);

			response.getWriter().println(GSON.toJson(result));
		} catch (Exception e) {
			AppServiceResult<CategoryDto> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Không thể cập nhật!", null);
			response.getWriter().write(GSON.toJson(result));
		}
	}

	private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			boolean active = request.getParameter("active").equals("1");
			AppBaseResult result = categoryService.updateStatus(id, active);

			response.getWriter().println(GSON.toJson(result));
		} catch (Exception e) {
			AppServiceResult<CategoryDto> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Không thể cập nhật!", null);
			response.getWriter().write(GSON.toJson(result));
		}
	}
	
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String string = request.getParameter("ids");
		Type type = new TypeToken<List<Long>>(){}.getType();
		List<Long> ids = GSON.fromJson(string, type);
		AppBaseResult result = AppBaseResult.GenarateIsSucceed();
		for (long id : ids) {
			result = categoryService.deleteCategory(id);

			if (!result.isSuccess()) break;
		}
		response.getWriter().println(GSON.toJson(result));
	}
}
