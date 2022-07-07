package vn.edu.hcmuaf.fit.controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.cart.CartItemDto;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet(name = "api-cart", urlPatterns = "/api/cart/*")
@MultipartConfig
public class CartAPI extends HttpServlet {
    private final Gson GSON = new GsonBuilder().serializeNulls().create();
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String method = request.getMethod();
        switch (method) {
            case "GET":
                getCart(request, response);
                break;
            case "POST":
                switch (request.getPathInfo()) {
                    case "/add":
                        addToCart(request, response);
                        break;
                    case "/checkout":
                        checkout(request, response);
                        break;
                }
                break;
            case "PUT":
                updateQuantity(request, response);
                break;
            case "DELETE":
                String[] pathParts = request.getPathInfo().split("/");
                switch (pathParts[1]) {
                    case "remove":
                        removeFromCart(request, response);
                        break;
                    case "remove-all":
                        removeAllFromCart(request, response);
                        break;
                }
                break;
        }
    }

    private void getCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");
        AppServiceResult<List<CartItemDto>> result = cartService.getCart(userId);
        session.setAttribute("cart", result.getData());
        response.getWriter().write(GSON.toJson(result));
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("user_id");
            Long id = Long.parseLong(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            AppBaseResult result = cartService.addToCart(userId, id, quantity);

            response.getWriter().write(GSON.toJson(result));
        } catch (Exception e) {
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể thêm vào giỏ hàng");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(GSON.toJson(result));
        }
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("user_id");

            String string = request.getParameter("productIds");
            Type type = new TypeToken<List<Long>>() {}.getType();
            List<Long> productIds = GSON.fromJson(string, type);

            AppServiceResult<List<CartItemDto>> result = cartService.getCartForCheckout(userId, productIds);
            if (result.isSuccess()) {
                session.setAttribute("checkout_item", result.getData());
            }

            response.getWriter().write(GSON.toJson(result));
        } catch (Exception e) {
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Sản phẩm không hợp lệ");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(GSON.toJson(result));
        }
    }

    private void updateQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("user_id");
            Long productId = Long.parseLong(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            AppBaseResult result = cartService.updateQuantity(userId, productId, quantity);

            response.getWriter().write(GSON.toJson(result));
        } catch (Exception e) {
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể cập nhật giỏ hàng");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(GSON.toJson(result));
        }
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("user_id");
            String string = request.getParameter("productIds");
            Type type = new TypeToken<List<Long>>(){}.getType();
            List<Long> productIds = GSON.fromJson(string, type);

            AppBaseResult result = new AppBaseResult(true, 0, "");
            for (Long productId : productIds) {
                result = cartService.removeFromCart(userId, productId);

                if (!result.isSuccess()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write(GSON.toJson(result));
                    return;
                }
            }

            response.getWriter().write(GSON.toJson(result));
        } catch (Exception e) {
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể xóa sản phẩm khỏi giỏ hàng");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(GSON.toJson(result));
        }
    }

    private void removeAllFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("user_id");

            AppBaseResult result = cartService.removeAllFromCart(userId);
            response.getWriter().write(GSON.toJson(result));
        } catch (Exception e) {
            AppBaseResult result = AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể xóa sản phẩm khỏi giỏ hàng");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(GSON.toJson(result));
        }
    }
}
