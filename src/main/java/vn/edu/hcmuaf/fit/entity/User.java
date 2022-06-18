package vn.edu.hcmuaf.fit.entity;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private Long id;
    private String lastName;
    private String firstName;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private boolean isMale;
    private String imageUrl;
    private Date dateCreated;
    private Date lastUpdated;
    private boolean isVerified;
    private Role role;
    private boolean isNotLocked;
    private boolean active;
    private Set<Wishlist> wishlists = new LinkedHashSet<>();
    private Set<Address> addresses = new LinkedHashSet<>();
    private Set<Order> orders = new LinkedHashSet<>();

    public User() {
    }

    public User(Long id, String lastName, String firstName, String fullName, String password, String email, String phone, Date dateOfBirth,
                boolean isMale, String imageUrl, Date dateCreated, Date lastUpdated, boolean isVerified,
                Role role, boolean isNotLocked, boolean active, Set<Wishlist> wishlists, Set<Address> addresses, Set<Order> orders) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.isMale = isMale;
        this.imageUrl = imageUrl;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.isVerified = isVerified;
        this.role = role;
        this.isNotLocked = isNotLocked;
        this.active = active;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
