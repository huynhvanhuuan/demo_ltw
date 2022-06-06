package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Trademark implements Serializable {
    private Long id;
    private String name;
    private String website;
    private Set<Address> addresses = new HashSet<>();

    public Trademark() {
    }

    public Trademark(Long id, String name, String website, Set<Address> addresses) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.addresses = addresses;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
