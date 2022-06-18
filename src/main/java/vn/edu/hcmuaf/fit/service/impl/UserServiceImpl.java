package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.dao.UserDAO;
import vn.edu.hcmuaf.fit.dao.impl.UserDAOImpl;
import vn.edu.hcmuaf.fit.service.UserService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDAOImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void register() {

    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

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
