package vn.edu.hcmuaf.fit.dto.address;

public class AddressUpdate {
    private Long id;
    private String number;
    private String street;
    private Long wardId;
    private Long districtId;
    private Long provinceId;

    public AddressUpdate() {
    }

    public AddressUpdate(Long id, String number, String street, Long wardId, Long districtId, Long provinceId) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.wardId = wardId;
        this.districtId = districtId;
        this.provinceId = provinceId;
    }

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
}
