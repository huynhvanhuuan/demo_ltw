package vn.edu.hcmuaf.fit.dto.appuser;

public class UserStatus {
    private Long userId;
    private Boolean isActive;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
