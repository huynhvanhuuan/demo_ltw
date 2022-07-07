package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class OrderItem implements Serializable {
	private Order order;
	private ProductDetail product;
	private Long unitPrice;
	private int quantity;
	
	public OrderItem() {
	}
	
	public OrderItem(Order order, ProductDetail product, Long unitPrice, int quantity) {
		this.order = order;
		this.product = product;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public ProductDetail getProduct() {
		return product;
	}
	
	public void setProduct(ProductDetail product) {
		this.product = product;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
