package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDAO extends BaseDAO<Order> {
    List<Order> findByUserId(Long userId);
    Order findByOrderTrackingNumber(UUID orderTrackingNumber);
    boolean updateStatus(Long orderId, Integer status);
    boolean updateAddress(Long orderId, String address);
}
