package vn.edu.hcmuaf.fit.controller.client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(name = "client-home", value = "/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
        response.setContentType("text/html");
        response.setHeader("Set-Cookie", "HttpOnly;Secure;SameSite=Strict");
        request.getRequestDispatcher("/view/client/home.jsp").forward(request, response);
    }

    private void getHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
//        List<Product> products = productService.getList();
//        request.setAttribute("products", products);
//
//        List<ProductDetail> productDetails = warehouseService.getProductList();
//        request.setAttribute("product-details", productDetails);
//
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("auth");
//
//        if (user != null) {
//            List<CartItem> carts = cartService.getList(user);
//            request.setAttribute("carts", carts);
//
//            Wishlist wishlist = wishlistService.getList(user);
//            request.setAttribute("wishlist", wishlist);
//        }

        request.getRequestDispatcher("/view/home.jsp").forward(request, response);
    }

    private void getProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/profile.jsp");
        dispatcher.forward(request, response);
    }

    private void getCartPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/cart.jsp");
        dispatcher.forward(request, response);
    }

    private void getWishlistPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/wishlist.jsp");
        dispatcher.forward(request, response);
    }

    private void getProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
        if (request.getParameter("id") == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/product.jsp");
            dispatcher.forward(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/product-detail.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void getContactPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/contact.jsp").forward(request, response);
    }

    private void getAboutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/about.jsp").forward(request, response);
    }

    private void getFAQPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/faq.jsp").forward(request, response);
    }

    private void getListProductData(HttpServletRequest request, HttpServletResponse response, int countProduct) throws SQLException, ParseException {
        /* HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("user_id");

        // get list product;
        List<Product> products = productService.getList(countProduct);
        request.setAttribute("products", products);

        // get products detail
        Map<Integer, Map<String, String>> productsDetails = productService.getListData(countProduct);
        request.setAttribute("product-details", productsDetails);

        // get cart
        Map<Integer, Map<String, String>> cart = productService.getCartOrWishlist(userId, true);
        request.setAttribute("cart", cart);

        // get wishlist
        Map<Integer, Map<String, String>> wishlist = productService.getCartOrWishlist(userId, false);
        request.setAttribute("wishlist", wishlist);*/
    }

    private void getListNewNDiscount(HttpServletRequest request, HttpServletResponse response, int countProduct) throws SQLException {
        /*// get list product new
        Map<Integer, Map<String, String>> listNew = productDAO.getListByCondition(true, countProduct);
        request.setAttribute("list_new", listNew);

        // get list product which has discount
        Map<Integer, Map<String, String>> listHasDiscount = productDAO.getListByCondition(false, countProduct);
        request.setAttribute("list_discount", listHasDiscount);*/
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException, IOException {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        PrintWriter out = response.getWriter();
    }

    private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
    }

    private void removeAllFromCart(HttpServletRequest request, HttpServletResponse response) throws SQLException {
    }
}
