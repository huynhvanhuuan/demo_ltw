package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.pagination.PageParam;
import vn.edu.hcmuaf.fit.dto.product.ProductDto;
import vn.edu.hcmuaf.fit.service.ProductDetailService;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.impl.ProductDetailServiceImpl;
import vn.edu.hcmuaf.fit.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductDetailService productDetailService = ProductDetailServiceImpl.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        String categoryId = request.getParameter("categoryId");
        String minPrice = request.getParameter("minPrice");
        String maxPrice = request.getParameter("maxPrice");
        String sort = request.getParameter("sort");

        AppServiceResult<List<ProductDto>> result = productService.getProducts(currentPage);
        if (result.isSuccess()) {
            request.setAttribute("products", result.getData());
            request.setAttribute("pageParam", new PageParam(productService.getTotalPage(), currentPage));
            request.getRequestDispatcher("/view/client/product.jsp").forward(request, response);
        } else {
            response.sendError(result.getErrorCode(), result.getMessage());
            request.getRequestDispatcher("/view/errors/500.jsp").forward(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        // Get part
        // Part part = request.getPart("image");
        // Get filename
        //String filename = part.getSubmittedFileName();
        // Setup path
        //String path = getFolderUpload().getAbsolutePath() + File.separator + System.currentTimeMillis() + "-" + filename;
        // Write file
        //part.write(path);
    }

    public File getFolderUpload() {
        File folderUpload = new File(System.getProperty("user.home") + "/Furniture Selling/images/product");
        if (!folderUpload.exists()) {
            if (folderUpload.mkdirs()) return folderUpload;
        }
        return folderUpload;
    }
}
