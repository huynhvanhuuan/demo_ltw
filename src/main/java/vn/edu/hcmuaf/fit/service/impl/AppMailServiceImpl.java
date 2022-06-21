package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.AppUserDAO;
import vn.edu.hcmuaf.fit.dao.VerificationTokenDAO;
import vn.edu.hcmuaf.fit.dao.impl.AppUserDAOImpl;
import vn.edu.hcmuaf.fit.dao.impl.VerificationTokenDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.entity.VerificationToken;
import vn.edu.hcmuaf.fit.service.AppMailService;
import vn.edu.hcmuaf.fit.util.AppUtils;
import vn.edu.hcmuaf.fit.util.DateUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class AppMailServiceImpl implements AppMailService {
    private static AppMailServiceImpl instance;
    private static final Properties props = new Properties();
    private static Session session;
    private static String username;
    private static String password;
    private static String baseUrlVerifyEmail;
    private final VerificationTokenDAO verificationTokenDAO;
    private final AppUserDAO appUserDAO;

    private AppMailServiceImpl() {
        init();
        verificationTokenDAO = VerificationTokenDAOImpl.getInstance();
        appUserDAO = AppUserDAOImpl.getInstance();

        ((VerificationTokenDAOImpl) verificationTokenDAO).setAppUserDAO(appUserDAO);
    }

    public static AppMailServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppMailServiceImpl();
        }
        return instance;
    }

    private void init() {
        try {
            props.load(AppMailServiceImpl.class.getClassLoader().getResourceAsStream("application.properties"));
            username = props.getProperty("mail.smtp.username");
            password = props.getProperty("mail.smtp.password");
            baseUrlVerifyEmail = props.getProperty("app.config.baseurl.verify.email");
            session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AppBaseResult sendMailVerify(AppUser appUser) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(UUID.randomUUID());
        verificationToken.setDateCreated(DateUtil.getNow());
        verificationToken.setAppUser(appUser);
        verificationToken.setVerified(Boolean.FALSE);

        boolean isSuccess = sendVerifyEmailRegister(appUser.getEmail(), verificationToken.getToken().toString());

        if (isSuccess) {
            verificationToken.setLastSent(DateUtil.getNow());
            verificationTokenDAO.save(verificationToken);
            return new AppBaseResult(true, 200, "Gửi mail thành công");
        }

        return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Gửi mail không thành công");
    }

    @Override
    public AppBaseResult resendMailVerify(String email) {
        AppUser appUser = appUserDAO.findByEmail(email);

        if (appUser == null) {
            return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Email chưa được đăng ký: " + email);
        }

        VerificationToken verificationToken = verificationTokenDAO.findByUserId(appUser.getId());

        verificationToken.setToken(UUID.randomUUID());

        boolean isSuccess = sendVerifyEmailRegister(appUser.getEmail(), verificationToken.getToken().toString());

        if (isSuccess) {
            verificationToken.setLastSent(DateUtil.getNow());
            verificationTokenDAO.save(verificationToken);
            return new AppBaseResult(true, 200, "Gửi mail thành công");
        }

        return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Gửi mail không thành công");
    }

    private boolean sendVerifyEmailRegister(String email, String token) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username,"Amanda"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Xác nhận tài khoản");
            message.setContent(AppUtils.getVerifyEmailContent(baseUrlVerifyEmail + token), "text/html; charset=utf-8");
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public AppBaseResult sendMailResetPassword(String email, String fullname, String newPassword) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username,"Amanda"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Đặt lại mật khẩu");
            message.setContent(AppUtils.getResetPasswordContent(fullname, newPassword), "text/html; charset=utf-8");
            Transport.send(message);

            return new AppBaseResult(true, 200, "Gửi mail thành công");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Gửi mail không thành công");
    }

    public static void main (String[] args) throws IOException {
         AppMailServiceImpl appMailService = AppMailServiceImpl.getInstance();
         System.out.println(appMailService.sendMailResetPassword("chilungdaica@gmail.com", "Dương Nguyễn Cẩm Nhung", "Anhyeuem &#10084;").isSuccess() ?"Send mail success":"Send mail fail");
    }
}
