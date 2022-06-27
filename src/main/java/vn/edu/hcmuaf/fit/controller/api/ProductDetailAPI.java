package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.service.ProductDetailService;
import vn.edu.hcmuaf.fit.service.impl.ProductDetailServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "api-product-detail", urlPatterns = "/api/product-detail/*")
public class ProductDetailAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private final ProductDetailService productDetailService = new ProductDetailServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            Long materialId = Long.parseLong(request.getParameter("materialId"));
            Long colorId = Long.parseLong(request.getParameter("colorId"));
            Long productId = Long.parseLong(request.getParameter("productId"));
        } catch (NumberFormatException e) {
            response.sendError(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
