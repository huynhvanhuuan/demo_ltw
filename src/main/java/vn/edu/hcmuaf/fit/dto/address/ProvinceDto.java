package vn.edu.hcmuaf.fit.dto.address;

import vn.edu.hcmuaf.fit.entity.District;
import vn.edu.hcmuaf.fit.entity.Province;

import java.util.ArrayList;
import java.util.List;

public class ProvinceDto {
    private Long id;
    private String name;
    private String prefix;
    private List<DistrictDto> districts = new ArrayList<>();

    public ProvinceDto() {
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

    public static ProvinceDto createFromEntity(Province src) {
        ProvinceDto dest = new ProvinceDto();

        dest.id = src.getId();
        dest.name = src.getName();
        dest.prefix = src.getPrefix();

        if (src.getDistricts() != null) {
            for (District district : src.getDistricts()) {
                dest.districts.add(DistrictDto.createFromEntity(district));
            }
        }

        return dest;
    }
}
