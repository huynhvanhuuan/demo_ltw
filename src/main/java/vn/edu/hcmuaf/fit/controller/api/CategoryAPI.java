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
import vn.edu.hcmuaf.fit.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "api-category", urlPatterns = "/api/category/*")
public class CategoryAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().create();
	private CategoryService categoryService;
	
	@Override
	public void init() throws ServletException {
		categoryService = new CategoryServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			AppServiceResult<List<CategoryDto>> result = categoryService.getCategories();
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().println(GSON.toJson(result.getData()));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		} else {
			try {
				Long id = Long.valueOf(pathInfo.substring(1));
				AppServiceResult<CategoryDto> result = categoryService.getCategory(id);
				if (result.isSuccess()) {
					response.setStatus(200);
					response.getWriter().println(GSON.toJson(result.getData()));
				} else {
					response.sendError(result.getErrorCode(), result.getMessage());
				}
			} catch (NumberFormatException e) {
				response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String sku = StringUtil.toStringWithoutSpaces(name).toUpperCase(Locale.ROOT);
		
		CategoryCreate newCategory = new CategoryCreate(sku, name);
		
		AppServiceResult<CategoryDto> result = categoryService.createCategory(newCategory);
		if (result.isSuccess()) {
			response.setStatus(200);
			response.getWriter().println(GSON.toJson(result));
		} else {
			response.sendError(result.getErrorCode(), result.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String json = StringUtil.getStringFromInputStream(request.getInputStream());
			Type type = new TypeToken<CategoryUpdate>() {}.getType();
			CategoryUpdate updateCategory = GSON.fromJson(json, type);

			AppBaseResult result = categoryService.updateCategory(updateCategory);
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().println(GSON.toJson(result));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		} catch (NumberFormatException e) {
			response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String string = request.getParameter("ids");
			Type type = new TypeToken<List<Long>>(){}.getType();
			List<Long> ids = GSON.fromJson(string, type);
			for (long id : ids) {
				AppBaseResult result = categoryService.deleteCategory(id);
				if (result.isSuccess()) {
					response.setStatus(200);
					response.getWriter().println(GSON.toJson(result));
				} else {
					response.sendError(result.getErrorCode(), result.getMessage());
				}
			}
		} catch (NumberFormatException e) {
			response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}
}
