package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "api-product", urlPatterns = "/api/product/*")
public class ProductAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            AppServiceResult<List<ProductDto>> result = productService.getProducts();
            if (result.isSuccess()) {
                response.setStatus(200);
                response.getWriter().println(GSON.toJson(result));
            } else {
                response.sendError(result.getErrorCode(), result.getMessage());
            }
        } else {
            try {
                Long id = Long.parseLong(pathInfo.substring(1));
                AppServiceResult<ProductDto> result = productService.getProduct(id);
                if (result.isSuccess()) {
                    response.setStatus(200);
                    response.getWriter().println(GSON.toJson(result));
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

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}