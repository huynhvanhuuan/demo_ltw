package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.entity.AppUser;

public interface AppMailService {
    AppBaseResult sendMailVerify(AppUser appUser);
    AppBaseResult resendMailVerify(AppUser appUser);
    AppBaseResult sendMailResetPassword(String email, String fullname, String newPassword);
}
