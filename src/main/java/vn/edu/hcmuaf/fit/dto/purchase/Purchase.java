package vn.edu.hcmuaf.fit.dto.purchase;

import vn.edu.hcmuaf.fit.dto.cart.CartItemDto;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.Order;

import java.util.List;

public class Purchase {
    private UserInfoDtoResponse userInfo;
    private Order order;
    private List<CartItemDto> items;

    public Purchase() {
    }

    public UserInfoDtoResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDtoResponse userInfo) {
        this.userInfo = userInfo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}
