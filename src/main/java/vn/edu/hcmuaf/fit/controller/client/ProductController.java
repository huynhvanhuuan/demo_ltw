package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.pagination.PageParam;
import vn.edu.hcmuaf.fit.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.impl.ProductServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
@MultipartConfig
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

            AppServiceResult<List<ProductDto>> result = productService.getProducts(currentPage);

            request.setAttribute("products", result.getData());
            request.setAttribute("pageParam", new PageParam(productService.getTotalPage(), currentPage));

            request.getRequestDispatcher("/view/client/product.jsp").forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
