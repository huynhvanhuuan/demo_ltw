package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.AppRoleDAO;
import vn.edu.hcmuaf.fit.dao.AppUserDAO;
import vn.edu.hcmuaf.fit.entity.AppRole;
import vn.edu.hcmuaf.fit.entity.AppUser;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.*;

public class AppRoleDAOImpl implements AppRoleDAO {
    private static AppRoleDAOImpl instance;
    private Connection connection;
    private AppUserDAO appUserDAO;

    private AppRoleDAOImpl() {
    }

    public static AppRoleDAOImpl getInstance() {
        if (instance == null) {
            instance = new AppRoleDAOImpl();
        }
        return instance;
    }

    public void setAppUserDAO(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @Override
    public List<AppRole> findAll() {
        List<AppRole> roles = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_ROLE.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Set<AppUser> appUsers = new LinkedHashSet<>(appUserDAO.findByRoleId(id));

                AppRole role = new AppRole(id, name, appUsers);
                roles.add(role);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return roles;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return roles;
    }

    @Override
    public AppRole findById(Long id) {
        AppRole appRole = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_ROLE.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String name = rs.getString("name");
                Set<AppUser> appUsers = new LinkedHashSet<>(appUserDAO.findByRoleId(id));

                appRole = new AppRole(id, name, appUsers);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appRole;
    }

    @Override
    public void save(AppRole object) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<AppRole> findByUserId(Long userId) {
        List<AppRole> roles = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_ROLE.FIND_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");

                AppRole role = new AppRole(id, name, null);
                roles.add(role);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return roles;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return roles;
    }

    @Override
    public AppRole findByName(String name) {
        AppRole appRole = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_ROLE.FIND_BY_NAME);
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                long id = rs.getLong("id");
                Set<AppUser> appUsers = new LinkedHashSet<>(appUserDAO.findByRoleId(id));

                appRole = new AppRole(id, name, appUsers);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appRole;
    }
}
