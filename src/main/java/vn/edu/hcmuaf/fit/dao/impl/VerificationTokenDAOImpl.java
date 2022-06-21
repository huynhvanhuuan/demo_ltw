package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.AppUserDAO;
import vn.edu.hcmuaf.fit.dao.VerificationTokenDAO;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.entity.VerificationToken;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class VerificationTokenDAOImpl implements VerificationTokenDAO {
    private static VerificationTokenDAOImpl instance;
    private Connection connection;
    private AppUserDAO appUserDAO;

    private VerificationTokenDAOImpl() {
    }

    public static VerificationTokenDAOImpl getInstance() {
        if (instance == null) {
            instance = new VerificationTokenDAOImpl();
        }
        return instance;
    }

    public void setAppUserDAO(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public List<VerificationToken> findAll() {
        return null;
    }

    @Override
    public VerificationToken findById(Long id) {
        return null;
    }

    @Override
    public void save(VerificationToken object) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public VerificationToken findByUserId(Long userId) {
        VerificationToken verificationToken = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.VERIFICATION_TOKEN.FIND_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                Long id = rs.getLong("id");
                UUID token = UUID.fromString(rs.getString("token"));
                Date dateCreated = rs.getTimestamp("date_created");
                Date lastSent = rs.getTimestamp("last_sent");
                Date verified_at = rs.getTimestamp("verified_at");
                Boolean isVerified = rs.getBoolean("is_verified");
                AppUser appUser = appUserDAO.findById(userId);
                verificationToken = new VerificationToken(id, token, dateCreated, lastSent, verified_at, isVerified, appUser);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return verificationToken;
    }
}
