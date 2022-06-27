package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private Long id;
    private String number;
    private String street;
    private Ward ward;
    private District district;
    private String path;
    private Boolean defaultAddress;

    public Address() {
    }

    public Address(Long id, String number, String street, Ward ward, District district, String path, Boolean defaultAddress) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.path = path;
        this.defaultAddress = defaultAddress;
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

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
