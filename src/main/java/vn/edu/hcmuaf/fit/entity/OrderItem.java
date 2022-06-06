package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private ProductDetail product;
	private int quantity;
	private Order order;
	
	public OrderItem() {
	}
	
	public OrderItem(ProductDetail product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public ProductDetail getProduct() {
		return product;
	}
	
	public void setProduct(ProductDetail product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
}
