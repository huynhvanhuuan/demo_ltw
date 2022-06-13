package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.*;

public class Product implements Serializable {
    private Long id;
    private String name;
    private String size;
    private String description;
    private Trademark trademark;
    private Category category;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean active;
    private Set<ProductDetail> products = new LinkedHashSet<>();

    public Product() {}

    public Product(Long id, String name, String size, String description, Trademark trademark, Category category,
                   Date dateCreated, Date lastUpdated, boolean active, Set<ProductDetail> products) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.description = description;
        this.category = category;
        this.trademark = trademark;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.active = active;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Trademark getTrademark() {
        return trademark;
    }

    public void setTrademark(Trademark trademark) {
        this.trademark = trademark;
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

    public Set<ProductDetail> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDetail> products) {
        this.products = products;
    }

    public void addProductDetail(ProductDetail productDetail) {
        if (products == null) products = new LinkedHashSet<>();
        products.add(productDetail);
        productDetail.setProduct(this);
    }

}
