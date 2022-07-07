package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.productDetail.ProductDetailDto;
import vn.edu.hcmuaf.fit.service.ProductDetailService;
import vn.edu.hcmuaf.fit.service.impl.ProductDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "api-product-detail", urlPatterns = "/api/product-detail/*")
public class ProductDetailAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private ProductDetailService productDetailService;

    @Override
    public void init() throws ServletException {
        productDetailService = new ProductDetailServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            getProductDetail(request, response);
            return;
        }

        String[] pathParts = path.split("/");
        String method = request.getMethod();
        switch (method) {
            case "GET":
                getProductDetail(request, response);
                break;
        }
    }

    private void getProductDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Long productId = Long.parseLong(request.getParameter("productId"));
            Long colorId = Long.parseLong(request.getParameter("colorId"));
            Long materialId = Long.parseLong(request.getParameter("materialId"));

            AppServiceResult<ProductDetailDto> result = productDetailService.getProductDetail(productId, colorId, materialId);
            response.getWriter().println(GSON.toJson(result));
        } catch (Exception e) {
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Dữ liệu không hợp lệ");
            response.getWriter().println(GSON.toJson(result));
        }
    }
}
