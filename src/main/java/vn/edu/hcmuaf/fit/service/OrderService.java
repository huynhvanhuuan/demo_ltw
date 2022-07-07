package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.order.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    AppServiceResult<List<OrderDto>> getOrders(Long userId);
    AppServiceResult<OrderDto> getOrder(UUID orderTrackingNumber);
    AppBaseResult updateStatus(Long orderId, Integer status);
    AppBaseResult updateAddress(Long orderId, String address);
    AppBaseResult removeOrder(Long orderId);
}
