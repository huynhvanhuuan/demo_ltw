package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.*;
import vn.edu.hcmuaf.fit.dto.category.CategoryUpdate;
import vn.edu.hcmuaf.fit.service.AddressService;
import vn.edu.hcmuaf.fit.service.impl.AddressServiceImpl;
import vn.edu.hcmuaf.fit.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(name = "api-address", urlPatterns = "/api/address/*")
@MultipartConfig
public class AddressAPI extends HttpServlet {
	private final AddressService addressService = AddressServiceImpl.getInstance();
	private final Gson GSON = new GsonBuilder().serializeNulls().create();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		long id;
		try {
			if (pathInfo == null || pathInfo.equals("/")) {
				AppServiceResult<List<AddressDto>> result = addressService.getAddresses();
				if (result.isSuccess()) {
					response.setStatus(200);
					response.getWriter().write(GSON.toJson(result));
				} else {
					response.sendError(result.getErrorCode(), result.getMessage());
				}
			} else if (pathInfo.contains("/t")) {
				id = Long.parseLong(pathInfo.substring(3));
				doGetTrademarkAddress(response, id);
			} else if (pathInfo.contains("/u")) {
				id = Long.parseLong(pathInfo.substring(3));
				doGetUserAddress(response, id);
			} else {
				id = Long.parseLong(pathInfo.substring(1));
				AppServiceResult<AddressDto> result = addressService.getAddress(id);
				if (result.isSuccess()) {
					response.setStatus(200);
					response.getWriter().write(GSON.toJson(result));
				} else {
					response.sendError(result.getErrorCode(), result.getMessage());
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}

	public void doGetTrademarkAddress(HttpServletResponse response, Long trademarkId) throws IOException {
		AppServiceResult<List<AddressDto>> result = addressService.getAddressByTrademarkId(trademarkId);
		if (result.isSuccess()) {
			response.setStatus(200);
			response.getWriter().println(GSON.toJson(result.getData()));
		} else {
			response.sendError(result.getErrorCode(), result.getMessage());
		}
	}

	private void doGetUserAddress(HttpServletResponse response, Long userId) throws IOException {
		AppServiceResult<List<AddressDto>> result = addressService.getAddressByUserId(userId);
		if (result.isSuccess()) {
			response.setStatus(200);
			response.getWriter().println(GSON.toJson(result.getData()));
		} else {
			response.sendError(result.getErrorCode(), result.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		try {
			AddressCreate newAddress = new AddressCreate();

			long provinceId = Long.parseLong(request.getParameter("provinceId"));
			long districtId = Long.parseLong(request.getParameter("districtId"));
			long wardId = Long.parseLong(request.getParameter("wardId"));
			String street = request.getParameter("street");
			String number = request.getParameter("number");

			switch (request.getPathInfo()) {
				case "/t":
					long trademarkId = Long.parseLong(request.getParameter("trademarkId"));
					newAddress = new AddressCreate(number, street, wardId, districtId, provinceId, true, trademarkId);
					break;
				case "/u":
					long userId = Long.parseLong(request.getParameter("userId"));
					newAddress = new AddressCreate(number, street, wardId, districtId, provinceId, false, userId);
					break;
			}

			AppServiceResult<AddressDto> result = addressService.createAddress(newAddress);
			if (result.isSuccess()) {
				response.setStatus(200);
				response.getWriter().println(GSON.toJson(result));
			} else {
				response.sendError(result.getErrorCode(), result.getMessage());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String json = StringUtil.getStringFromInputStream(request.getInputStream());
			Type type = new TypeToken<CategoryUpdate>() {}.getType();
			AddressUpdate addressUpdate = GSON.fromJson(json, type);

			AppBaseResult result = addressService.updateAddress(addressUpdate);
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
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String string = request.getParameter("ids");
			Type type = new TypeToken<List<Long>>(){}.getType();
			List<Long> ids = GSON.fromJson(string, type);
			for (long id : ids) {
				AppBaseResult result = addressService.deleteAddress(id);
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
