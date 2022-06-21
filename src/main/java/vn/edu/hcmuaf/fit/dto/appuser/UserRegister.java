package vn.edu.hcmuaf.fit.dto.appuser;

public class UserRegister {
    private String lastName; // Họ
    private String firstName; // Tên
    private String phone;
    private Boolean isMale;
    private String email;
    private String username;
    private String password;

    public UserRegister() {
    }

    public UserRegister(String lastName, String firstName, String phone, Boolean isMale, String email, String username, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.isMale = isMale;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
