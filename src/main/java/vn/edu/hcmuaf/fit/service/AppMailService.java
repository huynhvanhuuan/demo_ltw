package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.entity.AppUser;

public interface AppMailService {
    void sendMailVerify(AppUser appUser);

    void sendMailResetPassword(String email, String newPassword);
}
