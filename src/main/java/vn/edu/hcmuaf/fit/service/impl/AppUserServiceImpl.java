package vn.edu.hcmuaf.fit.service.impl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.constant.RoleConstant;
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

import java.io.File;
import java.math.BigInteger;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static vn.edu.hcmuaf.fit.constant.FileConstant.TEMP_PROFILE_IMAGE_BASE_URL;
import static vn.edu.hcmuaf.fit.constant.FileConstant.USER_IMAGE_FOLDER;

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

        appMailService = new AppMailServiceImpl();
    }

    @Override
    public AppBaseResult register(UserRegister userRegister) {
        try {
            AppUser userByUsername = appUserDAO.findByUsername(userRegister.getUsername());
            if (userByUsername != null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "T??n ????ng nh????p ??a?? t????n ta??i: " + userRegister.getUsername());
            }

            AppUser userByEmail = appUserDAO.findByEmail(userRegister.getEmail());
            if (userByEmail != null) {
                // logger.warn("Email is exist: " + userRegister.getEmail() + ", Cannot further process!");
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Email ??a?? t????n ta??i: " + userRegister.getEmail());
            }

            AppUser userByPhone = appUserDAO.findByPhone(userRegister.getPhone());
            if (userByPhone != null) {
                // logger.warn("Phone is exist: " + userRegister.getPhone() + ", Cannot further process!");
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "S???? ??i????n thoa??i ??a?? t????n ta??i: " + userRegister.getPhone());
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
            userNew.getUserInfo().setImageUrl(TEMP_PROFILE_IMAGE_BASE_URL + userRegister.getUsername());

            // Set default role
            AppRole defaultRole = appRoleDAO.findByName(RoleConstant.CUSTOMER);
            userNew.addRole(defaultRole);

            // Save new user info
            userInfoDAO.save(userNew.getUserInfo()); // save userinfo and get id back to user

            // Save new app user
            appUserDAO.save(userNew);

            // Send mail verify
            AppBaseResult result = appMailService.sendMailVerify(userNew);
            return new AppBaseResult(result.isSuccess(), result.getErrorCode(), result.isSuccess() ? "????ng ky?? tha??nh c??ng" : result.getMessage());
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
                        "Email kh??ng t????n ta??i: " + email);
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
                        "Ta??i khoa??n ??a?? ????????c xa??c th????c!");

            verificationToken.setVerified(Boolean.TRUE);
            verificationToken.setVerifiedAt(DateUtil.getNow());
            verificationToken.getAppUser().setEnabled(Boolean.TRUE);

            appUserDAO.save(verificationToken.getAppUser()); // active user

            verificationTokenDAO.save(verificationToken); // update verified token

            return AppBaseResult.GenarateIsSucceed();
        } else {
            return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                    "Ma?? xa??c nh????n kh??ng t????n ta??i!");
        }
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> getProfile(Long userId) {
        UserInfoDtoResponse userInfoDto = new UserInfoDtoResponse();
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Id kh??ng t????n ta??i: " + userId, null);
            }

            userInfoDto.setId(appUser.getId());
            userInfoDto.setUsername(appUser.getUsername());
            userInfoDto.setEmail(appUser.getEmail());
            userInfoDto.setPhone(appUser.getPhone());

            if (appUser.getUserInfo() != null) {
                userInfoDto.setFirstName(appUser.getUserInfo().getFirstName());
                userInfoDto.setLastName(appUser.getUserInfo().getLastName());
                userInfoDto.setFullName(appUser.getUserInfo().getFullName());
                userInfoDto.setDateOfBirth(appUser.getUserInfo().getDateOfBirth());
                userInfoDto.setMale(appUser.getUserInfo().isMale());
                userInfoDto.setImageUrl(appUser.getUserInfo().getImageUrl().contains(TEMP_PROFILE_IMAGE_BASE_URL)
                        ? TEMP_PROFILE_IMAGE_BASE_URL + appUser.getUsername() : appUser.getUserInfo().getImageUrl());
            }
            return new AppServiceResult<>(true, 0, "Success", userInfoDto);
        } catch (Exception e) {
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(), AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> saveProfile(UserInfoDtoRequest userInfo) {
        try {
            AppUser appUser = appUserDAO.findById(userInfo.getUserId());
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Id kh??ng t????n ta??i: " + userInfo.getUserId(), null);
            }

            appUser.getUserInfo().setFirstName(userInfo.getFirstName());
            appUser.getUserInfo().setLastName(userInfo.getLastName());
            appUser.getUserInfo().setFullName(userInfo.getFullName());
            appUser.getUserInfo().setMale(userInfo.isMale());
            appUser.getUserInfo().setDateOfBirth(userInfo.getDateOfBirth());

            userInfoDAO.save(appUser.getUserInfo());

            UserInfoDtoResponse userInfoDto = getProfile(userInfo.getUserId()).getData();
            return new AppServiceResult<>(true, 0, "C????p nh????t tha??nh c??ng!", userInfoDto);
        } catch (Exception e) {
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(), AppError.Unknown.errorMessage(), null);
        }
    }

    @Override
    public AppBaseResult changePassword(ChangePassword changePassword) {
        try {
            AppUser appUser = appUserDAO.findById(changePassword.getUserId());
            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "Id kh??ng t????n ta??i: " + changePassword.getUserId());
            }

            if (!appUser.getPassword().equals(hashPassword(changePassword.getCurrentPassword()))) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),
                        "M????t kh????u cu?? kh??ng chi??nh xa??c!");
            }

            appUser.setPassword(hashPassword(changePassword.getNewPassword()));
            appUserDAO.save(appUser);

            return new AppBaseResult(true, 0, "??????i m????t kh????u tha??nh c??ng.\n Vui lo??ng ????ng nh????p la??i!");
        } catch (Exception e) {
            e.printStackTrace();
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
        }
    }

    @Override
    public AppServiceResult<UserInfoDtoResponse> uploadImage(FileItem image, Long userId) {
        try {
            AppUser appUser = appUserDAO.findById(userId);

            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                        "Id kh??ng t????n ta??i: " + userId, null);
            }

            // constructs the directory path to store upload file
            Path imageFolder = Paths.get(USER_IMAGE_FOLDER).toAbsolutePath().normalize();
            if (!Files.exists(imageFolder)) {
                Files.createDirectories(imageFolder);
            }

            // delete old image if exists
            String currentImage = appUser.getUserInfo().getImageUrl();
            if (!appUser.getUserInfo().getImageUrl().contains(TEMP_PROFILE_IMAGE_BASE_URL)) {
                Files.deleteIfExists(Paths.get(imageFolder + File.separator + currentImage));
            }

            String fileName = new File(image.getName()).getName();
            String filePath = imageFolder + File.separator + fileName;

            String extension = FilenameUtils.getExtension(filePath);

            String newImage = DateUtil.toStringddMMyyyyHHmmss(new Date()) + "." + extension;
            File storeFile = new File(imageFolder + File.separator + newImage);

            // saves the image on disk
            image.write(storeFile);

            // save new image url to database
            appUser.getUserInfo().setImageUrl(newImage);

            userInfoDAO.save(appUser.getUserInfo());
            // appUserDAO.save(appUser);

            AppServiceResult<UserInfoDtoResponse> result = getProfile(userId);

            return new AppServiceResult<>(true, 0, "Ta??i h??nh ???nh tha??nh c??ng!", result.getData());
        } catch (Exception e) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Kh??ng th???? ta??i a??nh l??n!", null);
        }
    }

    @Override
    public AppBaseResult resetPassword(String email) {
        try {
            AppUser appUser = appUserDAO.findByEmail(email);

            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Email kh??ng t????n ta??i: " + email);
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
    public AppBaseResult updateStatus(UserStatus userStatus) {
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
                        "Sai ta??i khoa??n ho????c m????t kh????u!", null);
        }

        if (!appUser.getPassword().equals(hashPassword(userLogin.getPassword()))) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "Sai ta??i khoa??n ho????c m????t kh????u!", null);
        }

        if (!appUser.getEnabled()) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "Ta??i khoa??n ch??a ????????c xa??c th????c!", null);
        }

        if (!appUser.getNotLocked()) {
            return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                    "Ta??i khoa??n cu??a ba??n ??a?? bi?? khoa??!", null);
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
