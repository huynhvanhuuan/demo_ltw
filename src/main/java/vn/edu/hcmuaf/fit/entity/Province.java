package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Province implements Serializable {
    private long id;
    private String name;
    private String prefix;
    private Set<District> districts = new HashSet<>();

    public Province() {
    }

    public Province(long id, String name, String prefix, Set<District> districts) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.districts = districts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }
}
