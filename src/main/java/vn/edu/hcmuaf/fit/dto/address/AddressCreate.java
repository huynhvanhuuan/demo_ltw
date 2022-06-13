package vn.edu.hcmuaf.fit.dto.address;

public class AddressCreate {
    private String number;
    private String street;
    private Long wardId;
    private Long districtId;
    private Long provinceId;
    private boolean isCreateTrademark;
    private Long id;

    public AddressCreate() {}

    public AddressCreate(String number, String street, Long wardId, Long districtId, Long provinceId, boolean isCreateTrademark, Long id) {
        this.number = number;
        this.street = street;
        this.wardId = wardId;
        this.districtId = districtId;
        this.provinceId = provinceId;
        this.isCreateTrademark = isCreateTrademark;
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

    public boolean isCreateTrademark() {
        return isCreateTrademark;
    }

    public void setCreateTrademark(boolean createTrademark) {
        isCreateTrademark = createTrademark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
