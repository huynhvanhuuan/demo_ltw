package vn.edu.hcmuaf.fit.dto.cart;

import vn.edu.hcmuaf.fit.dto.productDetail.ProductDetailDto;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.entity.CartItem;

public class CartItemDto {
    private AppUser appUser;
    private ProductDetailDto product;
    private int quantity;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public ProductDetailDto getProduct() {
        return product;
    }

    public void setProduct(ProductDetailDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static CartItemDto createFromEntity(CartItem src) {
        CartItemDto dest = new CartItemDto();

        if (src.getAppUser() != null)
            dest.appUser = src.getAppUser();

        if (src.getProduct() != null)
            dest.product = ProductDetailDto.createFromEntity(src.getProduct());

        dest.quantity = src.getQuantity();

        return dest;
    }
}
