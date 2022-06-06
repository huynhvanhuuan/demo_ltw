package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class Ward implements Serializable {
    private Long id;
    private String name;
    private String prefix;
    private District district;

    public Ward() {
    }

    public Ward(Long id, String name, String prefix, District district) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.district = district;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
}
