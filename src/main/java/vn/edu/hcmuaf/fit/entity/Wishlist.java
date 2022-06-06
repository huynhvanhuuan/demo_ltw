package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class Wishlist implements Serializable {
	private User user;
	private ProductDetail productList;
	
	public Wishlist() {
	}
	
	public Wishlist(User user, ProductDetail productList) {
		this.user = user;
		this.productList = productList;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ProductDetail getListProduct() {
		return productList;
	}
	
	public void setListProduct(ProductDetail productList) {
		this.productList = productList;
	}

}
