package vn.edu.hcmuaf.fit.dto.product;

import vn.edu.hcmuaf.fit.entity.ProductDetail;

import java.util.Date;

public class ProductDetailDto {
    private Long id;
    private String sku;
    private ProductDto productDto;
    private ColorDto colorDto;
    private MaterialDto materialDto;
    private String imageUrl;
    private Long unitPrice;
    private int unitInStock;
    private int discount;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public ColorDto getColorDto() {
        return colorDto;
    }

    public void setColorDto(ColorDto colorDto) {
        this.colorDto = colorDto;
    }

    public MaterialDto getMaterialDto() {
        return materialDto;
    }

    public void setMaterialDto(MaterialDto materialDto) {
        this.materialDto = materialDto;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitInStock() {
        return unitInStock;
    }

    public void setUnitInStock(int unitInStock) {
        this.unitInStock = unitInStock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static ProductDetailDto createFromEntity(ProductDetail src) {
        ProductDetailDto dest = new ProductDetailDto();

        dest.id = src.getId();
        dest.sku = src.getSku();
        dest.productDto = ProductDto.createFromEntity(src.getProduct());
        dest.colorDto = ColorDto.createFromEntity(src.getColor());
        dest.materialDto = MaterialDto.createFromEntity(src.getMaterial());
        dest.imageUrl = src.getImageUrl();
        dest.unitPrice = src.getUnitPrice();
        dest.unitInStock = src.getUnitInStock();
        dest.discount = src.getDiscount();
        dest.dateCreated = src.getDateCreated();
        dest.lastUpdated = src.getLastUpdated();
        dest.active = src.isActive();

        return dest;
    }
}
