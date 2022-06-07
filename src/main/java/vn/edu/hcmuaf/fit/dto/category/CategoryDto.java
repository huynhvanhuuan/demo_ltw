package vn.edu.hcmuaf.fit.dto.category;

import vn.edu.hcmuaf.fit.entity.Category;

public class CategoryDto {

    private long id;
    private String sku;
    private String name;
    private boolean active;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static CategoryDto createFromEntity(Category src) {
        CategoryDto dest = new CategoryDto();

        dest.id = src.getId();
        dest.sku = src.getSku();
        dest.name = src.getName();
        dest.active = src.isActive();

        return dest;
    }
}
