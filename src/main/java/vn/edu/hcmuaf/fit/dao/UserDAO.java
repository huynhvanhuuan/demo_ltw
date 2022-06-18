package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.User;

public interface UserDAO extends BaseDAO<User> {
    User findByEmail(String email);
    User findByPhone(String phone);
}
