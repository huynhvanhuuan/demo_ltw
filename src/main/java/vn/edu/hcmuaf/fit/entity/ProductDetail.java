package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductDetail implements Serializable {
    private Long id;
    private String sku;
    private Product product;
    private Color color;
    private Material material;
    private String imageUrl;
    private Long unitPrice;
    private int unitInStock;
    private int discount;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean active;

    public ProductDetail() {
    }

    public ProductDetail(Long id, String sku, Product product, Color color, Material material, String imageUrl,
                         long unitPrice, int unitInStock, int discount, Date dateCreated, Date lastUpdated, boolean active) {
        this.id = id;
        this.sku = sku;
        this.product = product;
        this.color = color;
        this.material = material;
        this.imageUrl = imageUrl;
        this.unitPrice = unitPrice;
        this.unitInStock = unitInStock;
        this.discount = discount;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.active = active;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
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
}
