package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "client-home", value = "/home")
public class HomeController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // List<Product> products = productService.get();
        // request.setAttribute("products", products);
        request.getRequestDispatcher("/view/client/home.jsp").forward(request, response);
    }
}
