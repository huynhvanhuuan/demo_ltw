package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.AppUserRoleDAO;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;

public class AppUserRoleDAOImpl implements AppUserRoleDAO {
    private static AppUserRoleDAOImpl instance;
    private Connection connection;

    private AppUserRoleDAOImpl() {}

    public static AppUserRoleDAOImpl getInstance() {
        if (instance == null) {
            instance = new AppUserRoleDAOImpl();
        }
        return instance;
    }

    @Override
    public void save(Long appUserId, Long appRoleId) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER_ROLE.CREATE);
            statement.setLong(1, appUserId);
            statement.setLong(2, appRoleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void remove(Long appUserId, Long appRoleId) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER_ROLE.DELETE);
            statement.setLong(1, appUserId);
            statement.setLong(2, appRoleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void removeByUserId(Long userId) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER_ROLE.DELETE_BY_USER_ID);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }
}
