package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.AppUserDAO;
import vn.edu.hcmuaf.fit.dao.OrderDAO;
import vn.edu.hcmuaf.fit.dao.impl.AppUserDAOImpl;
import vn.edu.hcmuaf.fit.dao.impl.OrderDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.cart.CartItemDto;
import vn.edu.hcmuaf.fit.dto.purchase.Purchase;
import vn.edu.hcmuaf.fit.dto.purchase.PurchaseResponse;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.CheckoutService;

import java.util.List;
import java.util.UUID;

public class CheckoutServiceImpl implements CheckoutService {
    private final AppUserDAO appUserDAO;
    private final OrderDAO orderDAO;

    public CheckoutServiceImpl() {
        appUserDAO = AppUserDAOImpl.getInstance();
        orderDAO = OrderDAOImpl.getInstance();
    }

    @Override
    public AppServiceResult<PurchaseResponse> createOrder(Purchase purchase) {
        try {
            // Retrieve the order info from DTO
            Order order = purchase.getOrder();

            // Generate tracking number using UUID (Universally Unique Identifier) ver 4
            UUID orderTrackingNumber = UUID.randomUUID();
            order.setOrderTrackingNumber(orderTrackingNumber);

            // Get items
            List<CartItemDto> items = purchase.getItems();
            for (CartItemDto item : items) {
                OrderItem orderItem = new OrderItem();
                orderItem.setUnitPrice(item.getProduct().getTotalPrice());
                orderItem.setQuantity(item.getQuantity());

                ProductDetail product = new ProductDetail();
                product.setId(item.getProduct().getId());
                orderItem.setProduct(product);

                order.addItem(orderItem);
            }

            // Get customer
            AppUser appUser = appUserDAO.findById(purchase.getUserInfo().getId());
            order.setAppUser(appUser);

            orderDAO.save(order);

            return new AppServiceResult<>(true, 0, "Tạo đơn hàng thành công", new PurchaseResponse(orderTrackingNumber));
        } catch (Exception e) {
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Không thể tạo đơn hàng", null);
        }
    }
}
