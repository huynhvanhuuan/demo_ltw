package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {
	private String id;
	private AppUser appUser;
	private Long totalPrice;
	private String address;
	private Date dateCreated;
	private Date lastUpdated;
	private Set<OrderItem> items = new LinkedHashSet<>();
	
	public Order() {
	}
	
	public Order(String id, AppUser appUser, Long totalPrice, String address, Date dateCreated, Date lastUpdated, Set<OrderItem> items) {
		this.id = id;
		this.appUser = appUser;
		this.totalPrice = totalPrice;
		this.address = address;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.items = items;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	public Long getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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
	
	public Set<OrderItem> getOrderItems() {
		return items;
	}
	
	public void setOrderItems(Set<OrderItem> items) {
		this.items = items;
	}

	public void addItem(OrderItem item) {
		if (items == null) items = new LinkedHashSet<>();
		items.add(item);
		item.setOrder(this);
	}
}
