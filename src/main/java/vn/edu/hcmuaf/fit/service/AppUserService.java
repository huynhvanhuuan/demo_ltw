package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.appuser.AppUserForAdminDto;
import vn.edu.hcmuaf.fit.dto.appuser.UserRegister;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppBaseResult register(UserRegister userRegister);
    AppBaseResult verifyEmail(UUID token);
    AppBaseResult login();
    AppBaseResult logout();
    AppServiceResult<List<AppUserForAdminDto>> getUsers();
    AppServiceResult<AppUserForAdminDto> getUser(Long id);
}
