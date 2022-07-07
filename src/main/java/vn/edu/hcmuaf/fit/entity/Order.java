package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
	private Long id;
	private UUID orderTrackingNumber;
	private AppUser appUser;
	private String address;
	private Long totalPrice;
	private Long shippingFee;
	private Date dateCreated;
	private Date lastUpdated;
	private Integer status;
	private Set<OrderItem> items = new LinkedHashSet<>();
	
	public Order() {
	}
	
	public Order(Long id, UUID orderTrackingNumber, AppUser appUser, String address, Long totalPrice, Long shippingFee, Date dateCreated, Date lastUpdated, Integer status, Set<OrderItem> items) {
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
	
	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	public void addItem(OrderItem item) {
		if (items == null) items = new LinkedHashSet<>();
		items.add(item);
		item.setOrder(this);
	}
}
