package vn.edu.hcmuaf.fit.dto.product;

import vn.edu.hcmuaf.fit.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.dto.trademark.TrademarkDto;
import vn.edu.hcmuaf.fit.entity.Product;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    private long id;
    private String name;
    private String size;
    private String description;
    private TrademarkDto trademark;
    private CategoryDto category;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean active;
    private Set<ProductDetailDto> products = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public TrademarkDto getTrademark() {
        return trademark;
    }

    public void setTrademark(TrademarkDto trademark) {
        this.trademark = trademark;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
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

    public Set<ProductDetailDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDetailDto> products) {
        this.products = products;
    }

    public static ProductDto createFromEntity(Product src) {
        ProductDto dest = new ProductDto();

        dest.id = src.getId();
        dest.name = src.getName();
        dest.size = src.getSize();
        dest.description = src.getDescription();
        dest.trademark = TrademarkDto.createFromEntity(src.getTrademark());
        dest.category = CategoryDto.createFromEntity(src.getCategory());
        dest.dateCreated = src.getDateCreated();
        dest.lastUpdated = src.getLastUpdated();

        return dest;
    }
}
