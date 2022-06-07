package vn.edu.hcmuaf.fit.dto.address;

import vn.edu.hcmuaf.fit.entity.Ward;

public class WardDto {
    private Long id;
    private String name;
    private String prefix;
    private DistrictDto district;

    private WardDto() {
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public DistrictDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDto district) {
        this.district = district;
    }

    public static WardDto createFromEntity(Ward src) {
        WardDto ward = new WardDto();

        ward.id = src.getId();
        ward.name = src.getName();
        ward.prefix = src.getPrefix();

        if (src.getDistrict() != null) ward.district = DistrictDto.createFromEntity(src.getDistrict());

        return ward;
    }
}
