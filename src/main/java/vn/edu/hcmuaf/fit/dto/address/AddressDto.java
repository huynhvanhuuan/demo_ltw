package vn.edu.hcmuaf.fit.dto.address;

import vn.edu.hcmuaf.fit.entity.Address;

public class AddressDto {
    private Long id;
    private String number;
    private String street;
    private WardDto ward;
    private DistrictDto district;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public WardDto getWard() {
        return ward;
    }

    public void setWard(WardDto ward) {
        this.ward = ward;
    }

    public DistrictDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDto district) {
        this.district = district;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static AddressDto createFromEntity(Address address) {
        AddressDto dest = new AddressDto();

        dest.id = address.getId();
        dest.number = address.getNumber();
        dest.street = address.getStreet();
        dest.path = address.getPath();

        if (address.getWard() != null)
            dest.ward = WardDto.createFromEntity(address.getWard());

        if (address.getDistrict() != null)
            dest.district = DistrictDto.createFromEntity(address.getDistrict());

        return dest;
    }
}
