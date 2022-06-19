package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.*;

public class AppUser implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private Boolean notLocked;
    private Boolean enabled;
    private Set<AppRole> appRoles = new LinkedHashSet<>();
    private UserInfo userInfo;
    private Date dateCreated;
    private Date lastUpdated;
    private Set<Wishlist> wishlists = new LinkedHashSet<>();
    private Set<Address> addresses = new LinkedHashSet<>();
    private Set<Order> orders = new LinkedHashSet<>();

    public AppUser() {}

    public AppUser(Long id, String username, String email, String phone, String password, Boolean notLocked, Boolean enabled, Set<AppRole> appRoles, UserInfo userInfo, Date dateCreated, Date lastUpdated, Set<Wishlist> wishlists,
                   Set<Address> addresses, Set<Order> orders) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.notLocked = notLocked;
        this.enabled = enabled;
        this.appRoles = appRoles;
        this.userInfo = userInfo;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.wishlists = wishlists;
        this.addresses = addresses;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNotLocked() {
        return notLocked;
    }

    public void setNotLocked(Boolean notLocked) {
        this.notLocked = notLocked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AppRole> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(Set<AppRole> appRoles) {
        this.appRoles = appRoles;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Set<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(Set<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addRole(AppRole role) {
        if (appRoles == null) {
            appRoles = new LinkedHashSet<>();
        }

        this.appRoles.add(role);
    }
}
