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
