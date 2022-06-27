package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.*;
import vn.edu.hcmuaf.fit.service.AddressService;
import vn.edu.hcmuaf.fit.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-address", urlPatterns = "/api/address/*")
@MultipartConfig
public class AddressAPI extends HttpServlet {
	private final Gson GSON = new GsonBuilder().serializeNulls().create();
	private AddressService addressService;

	@Override
	public void init() throws ServletException {
		addressService = new AddressServiceImpl();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String path = request.getPathInfo();
		if (path == null || path.equals("/")) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Lỗi. Không tìm thấy đường dẫn!");
			response.getWriter().println(GSON.toJson(result));
			return;
		}

		String[] pathParts = path.split("/");
		String method = request.getMethod();
		switch (method) {
			case "GET":
				switch (pathParts[1]) {
					case "t":
						getTrademarkAddresses(request, response);
						break;
					/*case "u":
						getUserAddresses(request, response);
						break;*/
					default:
						if (pathParts.length == 2) {
							getAddress(response, pathParts[1]);
						} else {
							response.setStatus(HttpServletResponse.SC_NOT_FOUND);
							AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Lỗi. Không tìm thấy đường dẫn!");
							response.getWriter().println(GSON.toJson(result));
						}
						break;
				}
				break;
			case "POST":
				switch (pathParts[1]) {
					case "t":
						createAddressTrademark(request, response);
						break;
					case "u":
						createAddressUser(request, response);
						break;
					default:
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Lỗi. Không tìm thấy đường dẫn!");
						response.getWriter().println(GSON.toJson(result));
						break;
				}
				break;
			case "PUT":
				switch (pathParts[1]) {
					case "update":
						updateAddress(request, response);
						break;
					case "change-default":
						changeDefaultAddress(request, response);
						break;
					default:
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
						AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Lỗi. Không tìm thấy đường dẫn!");
						response.getWriter().println(GSON.toJson(result));
						break;
				}
				break;
			case "DELETE":
				deleteAddress(response, pathParts[1]);
				break;
		}
	}

	private void getTrademarkAddresses(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			Long trademarkId = Long.parseLong(request.getParameter("trademarkId"));

			AppServiceResult<List<AddressDto>> result = addressService.getAddressByTrademarkId(trademarkId);
			response.getWriter().println(GSON.toJson(result.getData()));
		} catch (Exception e) {
			AppServiceResult<List<AddressDto>> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Không thể tải địa chỉ!", null);
			response.getWriter().println(GSON.toJson(result));
		}
	}

	private void getUserAddresses(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			Long userId = (Long) session.getAttribute("user_id");

			AppServiceResult<List<AddressDto>> result = addressService.getAddressByUserId(userId);
			response.getWriter().println(GSON.toJson(result));
		} catch (Exception e) {
			AppServiceResult<List<AddressDto>> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Không thể tải địa chỉ!", null);
			response.getWriter().println(GSON.toJson(result));
		}
	}

	private void getAddress(HttpServletResponse response, String addressId) throws IOException {
		try {
			AppServiceResult<AddressDto> result = addressService.getAddress(Long.parseLong(addressId));
			response.getWriter().println(GSON.toJson(result));
		} catch (NumberFormatException e) {
			AppServiceResult<AddressDto> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Không thể tải địa chỉ!", null);
			response.getWriter().println(GSON.toJson(result));
		}
	}

	private void createAddressTrademark(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			long provinceId = Long.parseLong(request.getParameter("provinceId"));
			long districtId = Long.parseLong(request.getParameter("districtId"));
			long wardId = Long.parseLong(request.getParameter("wardId"));
			String street = request.getParameter("street");
			String number = request.getParameter("number");

			long trademarkId = Long.parseLong(request.getParameter("trademarkId"));
			AddressCreate newAddress = new AddressCreate(number, street, wardId, districtId, provinceId, trademarkId);

			AppServiceResult<AddressDto> result = addressService.createAddressForTrademark(newAddress);
			response.getWriter().println(GSON.toJson(result));
		} catch (NumberFormatException e) {
			AppServiceResult<AddressDto> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Dữ liệu không hợp lệ", null);
			response.getWriter().println(GSON.toJson(result));
		}
	}

	private void createAddressUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			long provinceId = Long.parseLong(request.getParameter("provinceId"));
			long districtId = Long.parseLong(request.getParameter("districtId"));
			long wardId = Long.parseLong(request.getParameter("wardId"));
			String street = request.getParameter("street");
			String number = request.getParameter("number");

			Long userId = (Long) request.getSession().getAttribute("user_id");
			AddressCreate newAddress = new AddressCreate(number, street, wardId, districtId, provinceId, userId);

			AppBaseResult result = addressService.createAddressForUser(newAddress);
			response.getWriter().println(GSON.toJson(result));
		} catch (Exception e) {
			AppServiceResult<AddressDto> result = new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Dữ liệu không hợp lệ", null);
			response.getWriter().println(GSON.toJson(result));
		}
	}

	private void updateAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			Long userId = (Long) session.getAttribute("user_id");

			Long id = Long.parseLong(request.getParameter("id"));
			Long provinceId = Long.parseLong(request.getParameter("provinceId"));
			Long districtId = Long.parseLong(request.getParameter("districtId"));
			Long wardId = Long.parseLong(request.getParameter("wardId"));
			String street = request.getParameter("street");
			String number = request.getParameter("number");
			Boolean defaultAddress = Boolean.parseBoolean(request.getParameter("defaultAddress"));

			AddressUpdate addressUpdate = new AddressUpdate(id, number, street, wardId, districtId, provinceId, defaultAddress, userId);

			AppBaseResult result = addressService.updateAddress(addressUpdate);
			response.getWriter().println(GSON.toJson(result));
		} catch (Exception e) {
			AppBaseResult result = new AppBaseResult(false, AppError.Unknown.errorCode(), "Dữ liệu không hợp lệ");
			response.getWriter().println(GSON.toJson(result));
		}
	}

	private void changeDefaultAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HttpSession session = request.getSession();
			Long userId = (Long) session.getAttribute("user_id");

			Long id = Long.parseLong(request.getParameter("id"));
			AppBaseResult result = addressService.changeDefaultAddress(id, userId);

			response.getWriter().println(GSON.toJson(result));
		} catch (NumberFormatException e) {
			AppBaseResult result = new AppBaseResult(false, AppError.Unknown.errorCode(), "Dữ liệu không hợp lệ");
			response.getWriter().println(GSON.toJson(result));
		}
	}
	
	private void deleteAddress(HttpServletResponse response, String id) throws IOException {
		try {
			AppBaseResult result = addressService.deleteAddress(Long.parseLong(id));
			response.getWriter().println(GSON.toJson(result));
		} catch (NumberFormatException e) {
			AppBaseResult result = new AppBaseResult(false, AppError.Unknown.errorCode(), "Id không hợp lệ");
			response.getWriter().println(GSON.toJson(result));
		}
	}
}
