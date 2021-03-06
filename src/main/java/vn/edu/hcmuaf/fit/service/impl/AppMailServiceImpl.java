package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.VerificationTokenDAO;
import vn.edu.hcmuaf.fit.dao.impl.AppUserDAOImpl;
import vn.edu.hcmuaf.fit.dao.impl.VerificationTokenDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.entity.VerificationToken;
import vn.edu.hcmuaf.fit.service.AppMailService;
import vn.edu.hcmuaf.fit.util.AppUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

public class AppMailServiceImpl implements AppMailService {
    private static final Properties props = new Properties();
    private static Session session;
    private static String username;
    private static String password;
    private static String baseUrlVerifyEmail;
    private final VerificationTokenDAO verificationTokenDAO;

    public AppMailServiceImpl() {
        init();
        verificationTokenDAO = VerificationTokenDAOImpl.getInstance();
        // appUserDAO = AppUserDAOImpl.getInstance();

        ((VerificationTokenDAOImpl) verificationTokenDAO).setAppUserDAO(AppUserDAOImpl.getInstance());
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
        verificationToken.setId(0L);
        verificationToken.setToken(UUID.randomUUID());
        verificationToken.setAppUser(appUser);

        boolean isSuccess = sendVerifyEmailRegister(appUser.getEmail(), verificationToken.getToken().toString());

        if (isSuccess) {
            verificationTokenDAO.save(verificationToken);
            return new AppBaseResult(true, 0, "G????i mail th??nh c??ng");
        }

        return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "G????i mail kh??ng tha??nh c??ng");
    }

    @Override
    public AppBaseResult resendMailVerify(AppUser appUser) {
        VerificationToken verificationToken = verificationTokenDAO.findByUserId(appUser.getId());

        if (verificationToken == null) return sendMailVerify(appUser);

        verificationToken.setToken(UUID.randomUUID());

        boolean isSuccess = sendVerifyEmailRegister(appUser.getEmail(), verificationToken.getToken().toString());

        if (isSuccess) {
            verificationTokenDAO.save(verificationToken);
            return new AppBaseResult(true, 0, "G????i mail th??nh c??ng");
        }

        return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "G????i mail kh??ng tha??nh c??ng");
    }

    private boolean sendVerifyEmailRegister(String email, String token) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username,"Amanda"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("X??c nh???n ta??i khoa??n", "UTF-8");
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
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username,"Amanda"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("?????t l???i m???t kh???u", "UTF-8");
            message.setContent(AppUtils.getResetPasswordContent(fullname, newPassword), "text/html; charset=utf-8");
            Transport.send(message);

            return new AppBaseResult(true, 0, "G????i mail th??nh c??ng");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "G????i mail kh??ng tha??nh c??ng");
    }
}
