package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;
import vn.edu.hcmuaf.fit.util.DateUtil;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class AppUserDAOImpl implements AppUserDAO {
    private static AppUserDAOImpl instance;
    private Connection connection;
    private UserInfoDAO userInfoDAO;
    private AppRoleDAO appRoleDAO;

    private AppUserDAOImpl() {
        userInfoDAO = UserInfoDAOImpl.getInstance();
        appRoleDAO = AppRoleDAOImpl.getInstance();
    }

    public static AppUserDAOImpl getInstance() {
        if (instance == null) {
            instance = new AppUserDAOImpl();
        }
        return instance;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }

    public void setRoleDAO(AppRoleDAO roleDAO) {
        this.appRoleDAO = roleDAO;
    }

    @Override
    public List<AppUser> findAll() {
        List<AppUser> appUsers = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                Boolean notLocked = rs.getBoolean("not_locked");
                Boolean enabled = rs.getBoolean("enabled");
                UserInfo userInfo = userInfoDAO.findById(rs.getLong("userinfo_id"));
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Set<AppRole> appRoles = new LinkedHashSet<>(appRoleDAO.findByUserId(id));

                AppUser appUser = new AppUser(id, username, email, phone, password, notLocked, enabled, appRoles, userInfo, dateCreated, lastUpdated, null, null, null);
                appUsers.add(appUser);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return appUsers;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appUsers;
    }

    @Override
    public AppUser findById(Long id) {
        AppUser user = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                Boolean notLocked = rs.getBoolean("not_locked");
                Boolean enabled = rs.getBoolean("enabled");
                UserInfo userInfo = userInfoDAO.findById(rs.getLong("userinfo_id"));
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Set<AppRole> appRoles = new LinkedHashSet<>(appRoleDAO.findByUserId(id));

                user = new AppUser(id, username, email, phone, password, notLocked, enabled, appRoles, userInfo, dateCreated, lastUpdated, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return user;
    }

    @Override
    public void save(AppUser appUser) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(appUser.getId() == 0 ? QUERY.APP_USER.CREATE : QUERY.APP_USER.UPDATE);
            statement.setString(1, appUser.getUsername());
            statement.setString(2, appUser.getEmail());
            statement.setString(3, appUser.getPhone());
            statement.setString(4, appUser.getPassword());
            statement.setBoolean(5, appUser.getNotLocked());
            statement.setBoolean(5, appUser.getEnabled());
            statement.setLong(6, appUser.getUserInfo().getId());
            statement.setString(7, DateUtil.toStringDatetime(appUser.getDateCreated()));
            statement.setString(8, DateUtil.toStringDatetime(appUser.getLastUpdated()));

            if (appUser.getId() != 0) {
                statement.setLong(16, appUser.getId());
            }
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<AppUser> findByRoleId(Long roleId) {
        List<AppUser> appUsers = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER.FIND_BY_ROLE_ID);
            statement.setLong(1, roleId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                Boolean notLocked = rs.getBoolean("not_locked");
                Boolean enabled = rs.getBoolean("enabled");
                UserInfo userInfo = userInfoDAO.findById(rs.getLong("userinfo_id"));
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));

                AppUser appUser = new AppUser(id, username, email, phone, password, notLocked, enabled, null,
                        userInfo, dateCreated, lastUpdated, null, null, null);
                appUsers.add(appUser);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return appUsers;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appUsers;
    }

    @Override
    public AppUser findByUsername(String username) {
        AppUser appUser = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER.FIND_BY_USERNAME);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                Boolean notLocked = rs.getBoolean("not_locked");
                Boolean enabled = rs.getBoolean("enabled");
                UserInfo userInfo = userInfoDAO.findById(rs.getLong("userinfo_id"));
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Set<AppRole> appRoles = new LinkedHashSet<>(appRoleDAO.findByUserId(id));

                appUser = new AppUser(id, username, email, phone, password, notLocked, enabled, appRoles,
                        userInfo, dateCreated, lastUpdated, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appUser;
    }

    @Override
    public AppUser findByEmail(String email) {
        AppUser appUser = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER.FIND_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                Boolean notLocked = rs.getBoolean("not_locked");
                Boolean enabled = rs.getBoolean("enabled");
                UserInfo userInfo = userInfoDAO.findById(rs.getLong("userinfo_id"));
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Set<AppRole> appRoles = new LinkedHashSet<>(appRoleDAO.findByUserId(id));

                appUser = new AppUser(id, username, email, phone, password, notLocked, enabled, appRoles,
                        userInfo, dateCreated, lastUpdated, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appUser;
    }

    @Override
    public AppUser findByPhone(String phone) {
        AppUser appUser = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.APP_USER.FIND_BY_PHONE);
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Boolean notLocked = rs.getBoolean("not_locked");
                Boolean enabled = rs.getBoolean("enabled");
                UserInfo userInfo = userInfoDAO.findById(rs.getLong("userinfo_id"));
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Set<AppRole> appRoles = new LinkedHashSet<>(appRoleDAO.findByUserId(id));

                appUser = new AppUser(id, username, email, phone, password, notLocked, enabled, appRoles,
                        userInfo, dateCreated, lastUpdated, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return appUser;
    }
}
