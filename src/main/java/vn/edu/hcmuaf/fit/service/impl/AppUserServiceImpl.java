package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.*;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.dao.impl.*;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.appuser.*;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoRequest;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.AppMailService;
import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.util.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AppUserServiceImpl implements AppUserService {
    private final AppUserDAO appUserDAO;
    private final UserInfoDAO userInfoDAO;
    private final AppRoleDAO appRoleDAO;
    private final VerificationTokenDAO verificationTokenDAO;
    private final AppMailService appMailService;

    public AppUserServiceImpl() {
        appUserDAO = AppUserDAOImpl.getInstance();
        userInfoDAO = UserInfoDAOImpl.getInstance();
        appRoleDAO = AppRoleDAOImpl.getInstance();
        verificationTokenDAO = VerificationTokenDAOImpl.getInstance();

        ((VerificationTokenDAOImpl) verificationTokenDAO).setAppUserDAO(appUserDAO);

        appMailService = AppMailServiceImpl.getInstance();
    }

    @Override
    public AppBaseResult register(UserRegister userRegister) {
        try {
            AppUser userByUsername = appUserDAO.findByUsername(userRegister.getUsername());
            if (userByUsername != null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Tên đăng nhập đã tồn tại: " + userRegister.getUsername());
            }

            AppUser userByEmail = appUserDAO.findByEmail(userRegister.getEmail());
            if (userByEmail != null) {
                // logger.warn("Email is exist: " + userRegister.getEmail() + ", Cannot further process!");
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Email đã tồn tại: " + userRegister.getEmail());
            }

            AppUser userByPhone = appUserDAO.findByPhone(userRegister.getPhone());
            if (userByPhone != null) {
                // logger.warn("Phone is exist: " + userRegister.getPhone() + ", Cannot further process!");
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Số điện thoại đã tồn tại: " + userRegister.getPhone());
            }

            AppUser userNew = new AppUser();
            userNew.setId(0L);
            userNew.setUsername(userRegister.getUsername());
            userNew.setEmail(userRegister.getEmail());
            userNew.setPhone(userRegister.getPhone());
            userNew.setPassword(hashPassword(userRegister.getPassword()));

            // Set new userinfo
            userNew.setUserInfo(new UserInfo());
            userNew.getUserInfo().setId(0L);
            userNew.getUserInfo().setFirstName(userRegister.getFirstName());
            userNew.getUserInfo().setLastName(userRegister.getLastName());
            userNew.getUserInfo().setFullName(AppUtils.getFullName(userRegister.getLastName(), userRegister.getFirstName()));
            userNew.getUserInfo().setMale(userRegister.isMale());
            userNew.getUserInfo().setImageUrl(FileConstant.TEMP_PROFILE_IMAGE_BASE_URL + userRegister.getUsername());

            // Set default role
            AppRole defaultRole = appRoleDAO.findByName(RoleConstant.CUSTOMER);
            userNew.addRole(defaultRole);

            // Save new user info
            userInfoDAO.save(userNew.getUserInfo()); // save userinfo and get id back to user

            // Save new app user
            appUserDAO.save(userNew);

            // Send mail verify
            return appMailService.sendMailVerify(userNew);
        } catch (Exception e) {
            e.printStackTrace();
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }

    @Override
    public AppBaseResult resendVerifyEmail(String email) {
        try {
            AppUser appUser = appUserDAO.findByEmail(email);
            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Email không tồn tại: " + email);
            }

            return appMailService.resendMailVerify(appUser);
        } catch (Exception e) {
            e.printStackTrace();
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }

    @Override
    public AppBaseResult verifyEmail(UUID token) {
        VerificationToken verificationToken = verificationTokenDAO.findByToken(token);

        if (verificationToken != null) {
            if (verificationToken.getVerified())
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Tài khoản đã được xác thực!");

            verificationToken.setVerified(Boolean.TRUE);
            verificationToken.setVerifiedAt(DateUtil.getNow());
            verificationToken.getAppUser().setEnabled(Boolean.TRUE);

            appUserDAO.save(verificationToken.getAppUser()); // active user

            verificationTokenDAO.save(verificationToken); // update verified token

            return AppBaseResult.GenarateIsSucceed();
        } else {
            return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                    "Mã xác nhận không tồn tại!");
        }
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> getProfile(Long userId) {
        UserInfoDtoResponse userInfoDto = new UserInfoDtoResponse();
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        AppError.Validation.errorMessage(), null);
            }

            userInfoDto.setId(appUser.getId());
            userInfoDto.setUsername(appUser.getUsername());
            userInfoDto.setEmail(appUser.getEmail());
            userInfoDto.setPhone(appUser.getPhone());

            if (appUser.getUserInfo() != null) {
                userInfoDto.setFirstName(appUser.getUserInfo().getFirstName());
                userInfoDto.setLastName(appUser.getUserInfo().getLastName());
                userInfoDto.setFullName(appUser.getUserInfo().getFullName());
                userInfoDto.setMale(appUser.getUserInfo().isMale());
                userInfoDto.setImageUrl(appUser.getUserInfo().getImageUrl().contains(FileConstant.TEMP_PROFILE_IMAGE_BASE_URL)
                        ? FileConstant.TEMP_PROFILE_IMAGE_BASE_URL + appUser.getUsername() : appUser.getUserInfo().getImageUrl());
            }
            return new AppServiceResult<>(true, 0, "Success", userInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> saveProfile(UserInfoDtoRequest userInfo) {
        return null;
    }

    @Override
    public AppBaseResult changePassword(ChangePassword changePassword) {
        try {
            AppUser appUser = appUserDAO.findById(changePassword.getUserId());
            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Id không tồn tại: " + changePassword.getUserId());
            }

            if (!appUser.getPassword().equals(hashPassword(changePassword.getCurrentPassword()))) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Mật khẩu cũ không chính xác!");
            }

            appUser.setPassword(hashPassword(changePassword.getNewPassword()));
            appUserDAO.save(appUser);

            return new AppBaseResult(true, 0, "Đổi mật khẩu thành công.\n Vui lòng đăng nhập lại!");
        } catch (Exception e) {
            e.printStackTrace();
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }

    @Override
    public AppBaseResult resetPassword(String email) {
        try {
            AppUser appUser = appUserDAO.findByEmail(email);

            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Email không tồn tại: " + email);
            }

            String newPassword = StringUtil.RandomString(6);
            appUser.setPassword(hashPassword(newPassword));

            appUserDAO.save(appUser);

            return appMailService.sendMailResetPassword(email, appUser.getUserInfo().getFullName(), newPassword);
        } catch (Exception e) {
            return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Email is not exist: " + email);
        }
    }

    @Override
    public AppBaseResult updateActive(UserStatus userStatus) {
        return null;
    }

    @Override
    public AppServiceResult<List<AppUserForAdminDto>> getUsers() {
        try {
            List<AppUser> appUsers = appUserDAO.findAll();
            List<AppUserForAdminDto> result = new ArrayList<>();

            appUsers.forEach(appUser -> result.add(AppUserForAdminDto.createFromEntity(appUser)));

            return new AppServiceResult<>(true, 0, "Success", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<AppUserForAdminDto> getUser(Long id) {
        try {
            AppUser appUser = appUserDAO.findById(id);
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        AppError.Validation.errorMessage(), null);
            }
            return new AppServiceResult<>(true, 0, "Success", AppUserForAdminDto.createFromEntity(appUser));
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<AppUser> getUserLogin(UserLogin userLogin) {
        AppUser appUser = appUserDAO.findByUsername(userLogin.getUsername());

        if (appUser == null) {
            appUser = appUserDAO.findByEmail(userLogin.getUsername());

            if (appUser == null) appUser = appUserDAO.findByPhone(userLogin.getUsername());
        }

        if (appUser == null) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Sai tài khoản hoặc mật khẩu!", null);
        }

        if (!appUser.getPassword().equals(hashPassword(userLogin.getPassword()))) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "Sai tài khoản hoặc mật khẩu!", null);
        }

        if (!appUser.getEnabled()) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "Tài khoản chưa được xác thực!", null);
        }

        if (!appUser.getNotLocked()) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "Tài khoản của bạn đã bị khoá!", null);
        }

        return new AppServiceResult<>(true, 0, "Success", appUser);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
