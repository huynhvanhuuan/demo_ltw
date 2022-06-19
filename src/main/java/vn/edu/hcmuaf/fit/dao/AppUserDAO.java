package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.AppUser;

import java.util.List;

public interface AppUserDAO extends BaseDAO<AppUser> {
    List<AppUser> findByRoleId(Long roleId);
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
    AppUser findByPhone(String phone);
}
