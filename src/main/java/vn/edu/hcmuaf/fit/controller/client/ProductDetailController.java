package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.product.*;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.impl.ProductServiceImpl;
import vn.edu.hcmuaf.fit.util.ProductUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "client-product-detail", value = "/product-detail")
public class ProductDetailController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        AppServiceResult<ProductDto> result = productService.getProduct(id);
        if (result.isSuccess()) {
            ProductDto product = result.getData();
            Map<Long, ColorDto> colors = new LinkedHashMap<>();
            Map<Long, MaterialDto> materials = new LinkedHashMap<>();
            Map<Long, List<String>> images = new LinkedHashMap<>();
            product.getProducts().forEach(detail -> {
                colors.put(detail.getColor().getId(), detail.getColor());
                materials.put(detail.getMaterial().getId(), detail.getMaterial());
                images.put(detail.getId(), ProductUtil.splitImageToList(detail.getImageUrl()));
            });
            request.setAttribute("product", product);
            request.setAttribute("colors", colors);
            request.setAttribute("materials", materials);
            request.setAttribute("images", images);
            request.getRequestDispatcher("/view/client/product-detail.jsp").forward(request, response);
    } else {
        response.sendError(result.getErrorCode(), result.getMessage());
        request.getRequestDispatcher("/view/errors/500.jsp").forward(request, response);
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
