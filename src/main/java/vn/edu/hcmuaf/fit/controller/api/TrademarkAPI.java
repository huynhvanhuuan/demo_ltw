package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.category.*;
import vn.edu.hcmuaf.fit.dto.trademark.*;
import vn.edu.hcmuaf.fit.service.TrademarkService;
import vn.edu.hcmuaf.fit.service.impl.TrademarkServiceImpl;
import vn.edu.hcmuaf.fit.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(name = "api-trademark", urlPatterns = "/api/trademark/*")
public class TrademarkAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().create();
	private TrademarkService trademarkService;
	
	@Override
	public void init() throws ServletException {
		trademarkService = new TrademarkServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			AppServiceResult<List<TrademarkDto>> result = trademarkService.getTrademarks();
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().println(GSON.toJson(result.getData()));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		} else {
			try {
				Long id = Long.valueOf(pathInfo.substring(1));
				AppServiceResult<TrademarkDto> result = trademarkService.getTrademark(id);
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
		String website = request.getParameter("website");
		
		TrademarkCreate newTrademark = new TrademarkCreate(name, website);
		
		AppServiceResult<TrademarkDto> result = trademarkService.createTrademark(newTrademark);
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
			TrademarkUpdate updateTrademark = GSON.fromJson(json, type);

			AppBaseResult result = trademarkService.updateTrademark(updateTrademark);
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
				AppBaseResult result = trademarkService.deleteTrademark(id);
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
