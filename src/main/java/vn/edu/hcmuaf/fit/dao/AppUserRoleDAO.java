package vn.edu.hcmuaf.fit.dao;

public interface AppUserRoleDAO {
    void save(Long appUserId, Long appRoleId);
    void remove(Long appUserId, Long appRoleId);
    void removeByUserId(Long userId);
}
