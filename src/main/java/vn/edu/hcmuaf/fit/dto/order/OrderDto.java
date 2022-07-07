package vn.edu.hcmuaf.fit.dto.order;

import vn.edu.hcmuaf.fit.constant.OrderMessage;
import vn.edu.hcmuaf.fit.dto.orderDetail.OrderItemDto;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.entity.Order;

import java.util.*;

public class OrderDto {
    private Long id;
    private UUID orderTrackingNumber;
    private AppUser appUser;
    private String address;
    private Long totalPrice;
    private Long shippingFee;
    private Date dateCreated;
    private Date lastUpdated;
    private Integer status;
    private Set<OrderItemDto> items = new LinkedHashSet<>();

    public OrderDto() {
    }

    public OrderDto(Long id, UUID orderTrackingNumber, AppUser appUser, String address, Long totalPrice, Long shippingFee, Date dateCreated, Date lastUpdated, Integer status, Set<OrderItemDto> items) {
        this.id = id;
        this.orderTrackingNumber = orderTrackingNumber;
        this.appUser = appUser;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shippingFee = shippingFee;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.status = status;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getOrderTrackingNumber() {
        return orderTrackingNumber;
    }

    public void setOrderTrackingNumber(UUID orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Long shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfoMessage() {
        switch (status) {
            case 1:
                return OrderMessage.Pending.getInfoMessage();
            case 2:
                return OrderMessage.Confirm.getInfoMessage();
            case 3:
                return OrderMessage.Delivering.getInfoMessage();
            case 4:
                return OrderMessage.Completed.getInfoMessage();
            case 5:
                return OrderMessage.Cancelled.getInfoMessage();
        }
        return null;
    }

    public String getSystemMessage() {
        switch (status) {
            case 1:
                return OrderMessage.Pending.getSystemMessage();
            case 2:
                return OrderMessage.Confirm.getSystemMessage();
            case 3:
                return OrderMessage.Delivering.getSystemMessage();
            case 4:
                return OrderMessage.Completed.getSystemMessage();
            case 5:
                return OrderMessage.Cancelled.getSystemMessage();
        }
        return null;
    }

    public Set<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemDto> items) {
        this.items = items;
    }

    public static OrderDto createFromEntity(Order src) {
        OrderDto dest = new OrderDto();

        dest.id = src.getId();
        dest.orderTrackingNumber = src.getOrderTrackingNumber();
        dest.address = src.getAddress();
        dest.totalPrice = src.getTotalPrice();
        dest.shippingFee = src.getShippingFee();
        dest.dateCreated = src.getDateCreated();
        dest.lastUpdated = src.getLastUpdated();
        dest.status = src.getStatus();

        if (src.getAppUser() != null) {
            dest.appUser = src.getAppUser();
        }

        if (src.getItems() != null) {
            src.getItems().forEach(item -> dest.items.add(OrderItemDto.createFromEntity(item)));
        }

        return dest;
    }
}
