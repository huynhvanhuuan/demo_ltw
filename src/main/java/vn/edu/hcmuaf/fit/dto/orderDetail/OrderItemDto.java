package vn.edu.hcmuaf.fit.dto.orderDetail;

import vn.edu.hcmuaf.fit.dto.productDetail.ProductDetailDto;
import vn.edu.hcmuaf.fit.entity.OrderItem;

public class OrderItemDto {
    private ProductDetailDto product;
    private Long unitPrice;
    private int quantity;

    public OrderItemDto() {
    }

    public OrderItemDto(ProductDetailDto product, Long unitPrice, int quantity) {
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public ProductDetailDto getProduct() {
        return product;
    }

    public void setProduct(ProductDetailDto product) {
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

    public static OrderItemDto createFromEntity(OrderItem src) {
        OrderItemDto dest = new OrderItemDto();

        dest.unitPrice = src.getUnitPrice();
        dest.quantity = src.getQuantity();

        if (src.getProduct() != null) {
            dest.product = ProductDetailDto.createFromEntity(src.getProduct());
        }

        return dest;
    }
}
