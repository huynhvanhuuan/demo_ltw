package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.OrderDAO;
import vn.edu.hcmuaf.fit.dao.impl.OrderDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.order.OrderDto;
import vn.edu.hcmuaf.fit.entity.Order;
import vn.edu.hcmuaf.fit.service.OrderService;

import java.util.*;

public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;

    public OrderServiceImpl() {
        this.orderDAO = OrderDAOImpl.getInstance();
    }

    @Override
    public AppServiceResult<List<OrderDto>> getOrders(Long userId) {
        try {
            List<Order> orders = orderDAO.findByUserId(userId);
            List<OrderDto> result = new ArrayList<>();

            orders.forEach(order -> result.add(OrderDto.createFromEntity(order)));

            return new AppServiceResult<>(true, 0, "Tải thành công", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    "Không thể tải danh sách đơn hàng", null);
        }
    }

    @Override
    public AppServiceResult<OrderDto> getOrder(UUID orderTrackingNumber) {
        Order order = orderDAO.findByOrderTrackingNumber(orderTrackingNumber);
        if (order == null) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "ID đơn hàng không tồn tại: " + orderTrackingNumber.toString(), null);
        }

        return new AppServiceResult<>(true, 0, "Tải thành công", OrderDto.createFromEntity(order));
    }

    @Override
    public AppBaseResult updateStatus(Long orderId, Integer status) {
        return null;
    }

    @Override
    public AppBaseResult updateAddress(Long orderId, String address) {
        return null;
    }

    @Override
    public AppBaseResult removeOrder(Long orderId) {
        return null;
    }
}
