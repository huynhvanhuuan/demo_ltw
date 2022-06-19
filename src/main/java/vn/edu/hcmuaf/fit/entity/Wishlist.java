package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class Wishlist implements Serializable {
	private UserInfo user;
	private ProductDetail productList;
	
	public Wishlist() {
	}
	
	public Wishlist(UserInfo user, ProductDetail productList) {
		this.user = user;
		this.productList = productList;
	}
	
	public UserInfo getUser() {
		return user;
	}
	
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	public ProductDetail getListProduct() {
		return productList;
	}
	
	public void setListProduct(ProductDetail productList) {
		this.productList = productList;
	}

}
