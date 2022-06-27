package vn.edu.hcmuaf.fit.dto.address;

public class AddressUpdate {
    private Long addressId;
    private String number;
    private String street;
    private Long wardId;
    private Long districtId;
    private Long provinceId;
    private Boolean defaultAddress;
    private Long id;

    public AddressUpdate() {
    }

    public AddressUpdate(Long addressId, String number, String street, Long wardId, Long districtId, Long provinceId, Boolean defaultAddress, Long id) {
        this.addressId = addressId;
        this.number = number;
        this.street = street;
        this.wardId = wardId;
        this.districtId = districtId;
        this.provinceId = provinceId;
        this.defaultAddress = defaultAddress;
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
