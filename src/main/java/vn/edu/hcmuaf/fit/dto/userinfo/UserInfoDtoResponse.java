package vn.edu.hcmuaf.fit.dto.userinfo;

import java.util.Date;

public class UserInfoDtoResponse {
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private String fullName;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private boolean male;
    private String imageUrl;

    public UserInfoDtoResponse() {
    }

    public UserInfoDtoResponse(Long id, String username, String lastName, String firstName, String fullName,
                               String email, String phone, Date dateOfBirth, boolean isMale, String imageUrl) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.male = isMale;
        this.imageUrl = imageUrl;
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
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
