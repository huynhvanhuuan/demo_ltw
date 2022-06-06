package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private Long id;
    private String number;
    private String street;
    private Ward ward;
    private District district;
    private String path;

    public Address() {
    }

    public Address(Long id, String number, String street, Ward ward, District district, String path) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.path = path;
    }

//    public Address(Long id, String number, String street, District district) {
//        this.id = id;
//        this.number = number;
//        this.street = street;
//        this.district = district;
//        Province province = district.getProvince();
//        if (number == null) {
//            if (district.getPrefix().isEmpty()) {
//                this.path = street + ", " + district.getName() + ", " + province.getPrefix() + " " + province.getName();
//            } else {
//                this.path = street + ", " + district.getPrefix() + " " + district.getName() + ", " + province.getPrefix() + " " + province.getName();
//            }
//        } else {
//            if (district.getPrefix().isEmpty()) {
//                this.path = number + ", " + street + ", " + district.getName() + ", " + province.getPrefix() + " " + province.getName();
//            } else {
//                this.path = number + ", " + street + ", " + district.getPrefix() + " " + district.getName() + ", " + province.getPrefix() + " " + province.getName();
//            }
//        }
//    }
//
//    public Address(Long id, String number, String street, Ward ward, District district) {
//        this.id = id;
//        this.number = number;
//        this.street = street;
//        this.ward = ward;
//        this.district = district;
//        Province province = district.getProvince();
//        if (district.getPrefix().isEmpty()) {
//            this.path = number + ", " + street + ", " + ward.getPrefix() + " " + ward.getName() + ", " +
//                    district.getName() + ", " + province.getPrefix() + " " + province.getName();
//        } else {
//            this.path = number + ", " + street + ", " + ward.getPrefix() + " " + ward.getName() + ", " + district.getPrefix() + " " +
//                    district.getName() + ", " + province.getPrefix() + " " + province.getName();
//        }
//    }

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
}
