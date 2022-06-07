package vn.edu.hcmuaf.fit.dto.address;

import vn.edu.hcmuaf.fit.entity.District;
import vn.edu.hcmuaf.fit.entity.Ward;

import java.util.ArrayList;
import java.util.List;

public class DistrictDto {
    private Long id;
    private String name;
    private String prefix;
    private ProvinceDto province;
    private List<WardDto> wards = new ArrayList<>();

    public DistrictDto() {
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

    public ProvinceDto getProvince() {
        return province;
    }

    public void setProvince(ProvinceDto province) {
        this.province = province;
    }

    public List<WardDto> getWards() {
        return wards;
    }

    public void setWards(List<WardDto> wards) {
        this.wards = wards;
    }

    public static DistrictDto createFromEntity(District src) {
        DistrictDto dest = new DistrictDto();

        dest.id = src.getId();
        dest.name = src.getName();
        dest.prefix = src.getPrefix();

        if (src.getProvince() != null) dest.province = ProvinceDto.createFromEntity(src.getProvince());

        if (src.getWards() != null) {
            for (Ward ward : src.getWards()) dest.wards.add(WardDto.createFromEntity(ward));
        }

        return dest;
    }
}
