package vn.edu.hcmuaf.fit.dto.checkout;

import vn.edu.hcmuaf.fit.entity.*;

import java.util.List;

public class Checkout {
    private UserInfo user;
    private String address;
    private Order order;
    private List<OrderItem> orderItems;

    public Checkout(UserInfo user, String address, Order order, List<OrderItem> orderItems) {
        this.user = user;
        this.address = address;
        this.order = order;
        this.orderItems = orderItems;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
