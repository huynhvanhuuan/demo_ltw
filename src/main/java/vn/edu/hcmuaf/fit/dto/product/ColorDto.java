package vn.edu.hcmuaf.fit.dto.product;

import vn.edu.hcmuaf.fit.entity.Color;

public class ColorDto {
    private Long id;
    private String name;
    private String hex;

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

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public static ColorDto createFromEntity(Color src) {
        ColorDto dest = new ColorDto();

        dest.id = src.getId();
        dest.name = src.getName();
        dest.hex = src.getHex();

        return dest;
    }
}
