package vn.edu.hcmuaf.fit.dto.checkout;

import vn.edu.hcmuaf.fit.entity.Order;
import vn.edu.hcmuaf.fit.entity.OrderItem;
import vn.edu.hcmuaf.fit.entity.User;

import java.util.List;

public class Checkout {
    private User user;
    private String address;
    private Order order;
    private List<OrderItem> orderItems;

    public Checkout(User user, String address, Order order, List<OrderItem> orderItems) {
        this.user = user;
        this.address = address;
        this.order = order;
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
