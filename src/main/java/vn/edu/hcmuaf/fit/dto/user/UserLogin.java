package vn.edu.hcmuaf.fit.dto.user;

public class UserLogin {
    private String username;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String password) {
        this.password = password;
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
