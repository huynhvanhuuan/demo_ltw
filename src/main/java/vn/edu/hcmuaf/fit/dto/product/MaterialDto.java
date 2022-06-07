package vn.edu.hcmuaf.fit.dto.product;

import vn.edu.hcmuaf.fit.entity.Material;

public class MaterialDto {
    private Long id;
    private String name;

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

    public static MaterialDto createFromEntity(Material src) {
        MaterialDto dest = new MaterialDto();

        dest.id = src.getId();
        dest.name = src.getName();

        return dest;
    }
}
