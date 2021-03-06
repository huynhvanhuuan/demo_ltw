package vn.edu.hcmuaf.fit.dto.product;

import vn.edu.hcmuaf.fit.dto.category.CategoryDto;
import vn.edu.hcmuaf.fit.dto.productDetail.ProductDetailDto;
import vn.edu.hcmuaf.fit.dto.trademark.TrademarkDto;
import vn.edu.hcmuaf.fit.entity.Product;
import vn.edu.hcmuaf.fit.entity.ProductDetail;

import java.util.*;

public class ProductDto {
    private Long id;
    private String name;
    private String size;
    private String description;
    private TrademarkDto trademark;
    private CategoryDto category;
    private Long shippingFee;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean active;
    private Set<ProductDetailDto> products = new LinkedHashSet<>();

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

    public Long getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Long shippingFee) {
        this.shippingFee = shippingFee;
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

    public int getMaxDiscount() {
        int maxDiscount = 0;
        for (ProductDetailDto productDetailDto : products) {
            if (productDetailDto.getDiscount() > maxDiscount) {
                maxDiscount = productDetailDto.getDiscount();
            }
        }
        return maxDiscount;
    }

    public long getDefaultMinPrice() {
        long defaultMinPrice = Long.MAX_VALUE;
        for (ProductDetailDto productDetailDto : products) {
            if (productDetailDto.getUnitPrice() < defaultMinPrice) {
                defaultMinPrice = productDetailDto.getUnitPrice();
            }
        }
        return defaultMinPrice;
    }

    public long getMaxPrice() {
        long maxPrice = Long.MIN_VALUE;
        for (ProductDetailDto productDetailDto : products) {
            if (productDetailDto.getUnitPrice() > maxPrice) {
                maxPrice = productDetailDto.getUnitPrice();
            }
        }
        return maxPrice;
    }

    public long getMinPrice() {
        long minPrice = Long.MAX_VALUE;
        for (ProductDetailDto productDetailDto : products) {
            if (productDetailDto.getTotalPrice() < minPrice) {
                minPrice = productDetailDto.getTotalPrice();
            }
        }
        return minPrice;
    }

    public static ProductDto createFromEntity(Product src) {
        ProductDto dest = new ProductDto();

        dest.id = src.getId();
        dest.name = src.getName();
        dest.size = src.getSize();
        dest.description = src.getDescription();
        dest.shippingFee = src.getShippingFee();
        dest.dateCreated = src.getDateCreated();
        dest.lastUpdated = src.getLastUpdated();
        dest.active = src.isActive();

        if (src.getTrademark() != null) {
            dest.trademark = TrademarkDto.createFromEntity(src.getTrademark());
        }

        if (src.getCategory() != null) {
            dest.category = CategoryDto.createFromEntity(src.getCategory());
        }

        if (src.getProducts() != null)
            for (ProductDetail productDetail : src.getProducts()) {
                dest.products.add(ProductDetailDto.createFromEntity(productDetail));
            }
        return dest;
    }
}
