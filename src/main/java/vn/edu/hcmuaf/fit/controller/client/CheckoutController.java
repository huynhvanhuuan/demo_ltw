package vn.edu.hcmuaf.fit.controller.client;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.address.AddressDto;
import vn.edu.hcmuaf.fit.dto.cart.CartItemDto;
import vn.edu.hcmuaf.fit.dto.purchase.Purchase;
import vn.edu.hcmuaf.fit.dto.purchase.PurchaseResponse;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.Order;
import vn.edu.hcmuaf.fit.service.*;
import vn.edu.hcmuaf.fit.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "client-checkout", value = "/checkout")
public class CheckoutController extends HttpServlet {
    private AddressService addressService;
    private CheckoutService checkoutService;
    private CartService cartService;
    private ProductDetailService productDetailService;

    @Override
    public void init() throws ServletException {
        addressService = new AddressServiceImpl();
        checkoutService = new CheckoutServiceImpl();
        cartService = new CartServiceImpl();
        productDetailService = new ProductDetailServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user_id");

        AppServiceResult<List<AddressDto>> addresses = addressService.getAddressByUserId(userId);
        if (addresses.isSuccess()) {
            request.setAttribute("addresses", addresses.getData());
            addresses.getData().stream().findFirst().ifPresent(address -> {
                if (address.getDefaultAddress())
                    request.setAttribute("address", address);
            });
        }

        request.getRequestDispatcher("/view/client/checkout.jsp").forward(request, response);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            UserInfoDtoResponse userInfo = (UserInfoDtoResponse) session.getAttribute("user");
            String type = request.getParameter("type");
            List<CartItemDto> items = (ArrayList<CartItemDto>) session.getAttribute("checkout_item");

            String address = request.getParameter("address");
            long totalPrice = Long.parseLong(request.getParameter("total_price"));
            long shippingFee = Long.parseLong(request.getParameter("shipping_fee"));

            Order order = new Order();
            order.setAddress(address);
            order.setTotalPrice(totalPrice);
            order.setShippingFee(totalPrice > 5000000 ? 0 : shippingFee); // can create a model for this

            Purchase purchase = new Purchase();
            purchase.setOrder(order);
            purchase.setUserInfo(userInfo);
            purchase.setItems(items);

            AppServiceResult<PurchaseResponse> result = checkoutService.createOrder(purchase);

            if (result.isSuccess()) {
                Map<Long, Integer> products = items.stream().collect(Collectors.toMap(item -> item.getProduct().getId(), CartItemDto::getQuantity));
                products.forEach((id, quantity) -> {
                    if (type == null || !type.equals("buy-now")) {
                        cartService.removeFromCart(userInfo.getId(), id);
                    }

                    productDetailService.updateQuantity(id, quantity, false);
                });  // update quantity of product

                session.removeAttribute("checkout_item");
                request.setAttribute("purchase", result.getData());
                request.getRequestDispatcher("/view/client/checkout-success.jsp").forward(request, response);
            } else {
                request.setAttribute("error", result.getMessage());
                request.getRequestDispatcher("/view/client/checkout.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
