package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.appuser.*;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoRequest;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.AppUser;

import java.util.List;
import java.util.UUID;

public interface AppUserService {
    AppServiceResult<List<AppUserForAdminDto>> getUsers();
    AppServiceResult<AppUserForAdminDto> getUser(Long id);
    AppServiceResult<AppUser> getUserLogin(UserLogin userLogin);
    AppBaseResult register(UserRegister userRegister);
    AppBaseResult resendVerifyEmail(String email);
    AppBaseResult verifyEmail(UUID token);
    AppServiceResult<UserInfoDtoResponse> getProfile(Long userId);
    AppServiceResult<UserInfoDtoResponse> saveProfile(UserInfoDtoRequest userInfo);
    AppBaseResult changePassword(ChangePassword changePassword);
    // AppServiceResult<String> uploadImage(MultipartFile file);
    AppBaseResult resetPassword(String email);
    AppBaseResult updateActive(UserStatus userStatus);
}
