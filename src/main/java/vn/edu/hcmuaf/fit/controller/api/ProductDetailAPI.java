package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.productDetail.ProductDetailDto;
import vn.edu.hcmuaf.fit.service.ProductDetailService;
import vn.edu.hcmuaf.fit.service.impl.ProductDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-product-detail", urlPatterns = "/api/product-detail/*")
public class ProductDetailAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private ProductDetailService productDetailService;

    @Override
    public void init() throws ServletException {
        productDetailService = new ProductDetailServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            AppServiceResult<List<ProductDetailDto>> result = productDetailService.getProductDetails(null);
            if (result.isSuccess()) {
                response.setStatus(200);
                response.getWriter().println(GSON.toJson(result));
            } else {
                response.sendError(result.getErrorCode(), result.getMessage());
            }
        } else {
            try {
                if (pathInfo.contains("/p")) {
                    Long id = Long.parseLong(pathInfo.substring(3));
                    AppServiceResult<ProductDetailDto> result = productDetailService.getProductDetail(id);
                    if (result.isSuccess()) {
                        response.setStatus(200);
                        response.getWriter().println(GSON.toJson(result));
                    } else {
                        response.sendError(result.getErrorCode(), result.getMessage());
                    }
                } else {
                    Long id = Long.parseLong(pathInfo.substring(1));
                    AppServiceResult<ProductDetailDto> result = productDetailService.getProductDetail(id);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
