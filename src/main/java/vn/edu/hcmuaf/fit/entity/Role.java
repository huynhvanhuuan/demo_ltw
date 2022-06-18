package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private long id;
    private String name;
    private String suffix;

    public Role() {
    }

    public Role(long id, String name, String suffix) {
        this.id = id;
        this.name = name;
        this.suffix = suffix;
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

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
