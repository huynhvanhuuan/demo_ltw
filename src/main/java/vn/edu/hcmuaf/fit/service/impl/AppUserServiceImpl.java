package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.*;
import vn.edu.hcmuaf.fit.dao.AppRoleDAO;
import vn.edu.hcmuaf.fit.dao.AppUserDAO;
import vn.edu.hcmuaf.fit.dao.impl.AppRoleDAOImpl;
import vn.edu.hcmuaf.fit.dao.impl.AppUserDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.appuser.*;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoRequest;
import vn.edu.hcmuaf.fit.dto.userinfo.UserInfoDtoResponse;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.AppMailService;
import vn.edu.hcmuaf.fit.service.AppUserService;
import vn.edu.hcmuaf.fit.util.AppUtils;
import vn.edu.hcmuaf.fit.util.StringUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AppUserServiceImpl implements AppUserService {
    private static AppUserServiceImpl instance;
    private final AppUserDAO appUserDAO;
    private final AppRoleDAO appRoleDAO;
    private final AppMailService appMailService;

    private AppUserServiceImpl() {
        appUserDAO = AppUserDAOImpl.getInstance();
        appRoleDAO = AppRoleDAOImpl.getInstance();
        appMailService = AppMailServiceImpl.getInstance();
    }

    public static AppUserServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppUserServiceImpl();
        }
        return instance;
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

            // Save new user
            appUserDAO.save(userNew);

            // Send mail verify
            appMailService.sendMailVerify(userNew);

            return new AppBaseResult(true, 200, "Đăng ký thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }

    @Override
    public AppBaseResult verifyEmail(UUID token) {
        return null;
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> getProfile(Long userId) {
        return null;
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> saveProfile(UserInfoDtoRequest userInfo) {
        return null;
    }

    @Override
    public AppBaseResult changePassword(ChangePassword changePassword) {
        return null;
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

            return new AppServiceResult<>(true, 0, "Succeed!", result);
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
            return new AppServiceResult<>(true, 0, "Succeed!", AppUserForAdminDto.createFromEntity(appUser));
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    AppError.Unknown.errorMessage(), null);
        }
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
