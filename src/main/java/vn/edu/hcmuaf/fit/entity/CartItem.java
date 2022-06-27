package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private AppUser appUser;
    private ProductDetail product;
    private int quantity;

    public CartItem() {
    }

    public CartItem(AppUser appUser, ProductDetail product, int quantity) {
        this.appUser = appUser;
        this.product = product;
        this.quantity = quantity;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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

    public double getTotalPrice() {
        return quantity * product.getDiscountPrice();
    }
}
