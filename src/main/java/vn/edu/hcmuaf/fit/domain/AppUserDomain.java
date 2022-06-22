package vn.edu.hcmuaf.fit.domain;

import vn.edu.hcmuaf.fit.domain.authority.GrantedAuthority;
import vn.edu.hcmuaf.fit.domain.authority.SimpleGrantedAuthority;
import vn.edu.hcmuaf.fit.entity.AppUser;

import java.util.*;

public class AppUserDomain {
    private AppUser appUser;

    public AppUserDomain(AppUser appUser) {
        this.appUser = appUser;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if (appUser.getAppRoles() != null)
            appUser.getAppRoles().forEach(item -> {
                grantedAuthorityList.add(new SimpleGrantedAuthority(item.getName()));
            });

        return grantedAuthorityList;
    }

    public String getPassword() {
        return this.appUser.getPassword();
    }

    public String getUsername() {
        return this.appUser.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return this.appUser.getNotLocked();
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return this.appUser.getEnabled();
    }

    public String getEmail() {
        return this.appUser.getEmail();
    }

    public Long getUserId() {
        return this.appUser.getId();
    }
}
