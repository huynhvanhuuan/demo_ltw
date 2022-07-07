package vn.edu.hcmuaf.fit.dto.appuser;

import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.*;

import java.util.ArrayList;
import java.util.List;

public class AppUserDto {
    private UserInfoDtoResponse userInfo;
    private List<AppRoleDto> appRoles = new ArrayList<>();
    private List<Wishlist> wishlists = new ArrayList<>();
    private List<Address> addresses = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private List<CartItem> cart = new ArrayList<>();

    public List<AppRoleDto> getAppRoles() {
        return appRoles;
    }

    public void setAppRoles(List<AppRoleDto> appRoles) {
        this.appRoles = appRoles;
    }

    public UserInfoDtoResponse getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDtoResponse userInfo) {
        this.userInfo = userInfo;
    }

    public static AppUserDto createFromEntity(AppUser src) {
        AppUserDto dest = new AppUserDto();

        if (src.getUserInfo() != null) {
            dest.userInfo = UserInfoDtoResponse.createFromEntity(src);
        }

        if (src.getAppRoles() != null)
            src.getAppRoles().forEach(role -> dest.appRoles.add(AppRoleDto.createFromEntity(role)));

        // if (src.getUserInfo() != null)
            // dest.userInfo = src.getUserInfo();

        return dest;
    }
}
