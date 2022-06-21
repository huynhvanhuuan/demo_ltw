package vn.edu.hcmuaf.fit.dto.appuser;

import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoForAdminDtoResponse;
import vn.edu.hcmuaf.fit.entity.AppUser;

import java.util.*;

public class AppUserForAdminDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Boolean notLocked;
    private Boolean enabled;
    private List<AppRoleDto> appRoles = new ArrayList<>();
    private UserInfoForAdminDtoResponse userInfo;
    private Date dateCreated;
    private Date lastUpdated;

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

    public List<AppRoleDto> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(List<AppRoleDto> appRoles) {
        this.appRoles = appRoles;
    }

    public UserInfoForAdminDtoResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoForAdminDtoResponse userInfo) {
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

    public static AppUserForAdminDto createFromEntity(AppUser src) {
        AppUserForAdminDto dest = new AppUserForAdminDto();

        dest.id = src.getId();
        dest.username = src.getUsername();
        dest.email = src.getEmail();
        dest.phone = src.getPhone();
        dest.notLocked = src.getNotLocked();
        dest.enabled = src.getEnabled();
        dest.dateCreated = src.getDateCreated();
        dest.lastUpdated = src.getLastUpdated();

        if (src.getAppRoles() != null)
            src.getAppRoles().forEach(role -> dest.appRoles.add(AppRoleDto.createFromEntity(role)));

        if (src.getUserInfo() != null)
            dest.userInfo = UserInfoForAdminDtoResponse.createFromEntity(src.getUserInfo());

        return dest;
    }
}
