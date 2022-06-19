package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class AppRole implements Serializable {
    private Long id;
    private String name;

    private Set<AppUser> users = new LinkedHashSet<>();

    public AppRole() {
    }

    public AppRole(Long id, String name, Set<AppUser> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }
}
