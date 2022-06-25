package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;
import vn.edu.hcmuaf.fit.util.DateUtil;

import java.sql.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppUserDAOImpl implements AppUserDAO {
    private static AppUserDAOImpl instance;
    private Connection connection;
    private UserInfoDAO userInfoDAO;
    private AppRoleDAO appRoleDAO;
    private AppUserRoleDAO appUserRoleDAO;

    private AppUserDAOImpl() {
        userInfoDAO = UserInfoDAOImpl.getInstance();
        appRoleDAO = AppRoleDAOImpl.getInstance();
        appUserRoleDAO = AppUserRoleDAOImpl.getInstance();

        ((AppRoleDAOImpl) appRoleDAO).setAppUserDAO(this);
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

    public void setUserRoleDAO(AppUserRoleDAO userRoleDAO) {
        this.appUserRoleDAO = userRoleDAO;
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
            PreparedStatement statement;
            if (appUser.getId() == 0) {
                userInfoDAO.save(appUser.getUserInfo()); // save new user info and get id
                statement = connection.prepareStatement(QUERY.APP_USER.CREATE, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, appUser.getUsername());
                statement.setString(2, appUser.getEmail());
                statement.setString(3, appUser.getPhone());
                statement.setString(4, appUser.getPassword());
                statement.setLong(5, appUser.getUserInfo().getId());
            } else {
                userInfoDAO.save(appUser.getUserInfo()); // update user info and get id
                statement = connection.prepareStatement(QUERY.APP_USER.UPDATE);
                statement.setString(1, appUser.getPassword());
                statement.setBoolean(2, appUser.getNotLocked());
                statement.setBoolean(3, appUser.getEnabled());
                statement.setLong(4, appUser.getId());
            }
            statement.executeUpdate();

            if (appUser.getId() == 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    appUser.setId(rs.getLong(1));
                }
            }

            // update role
            List<AppRole> appRoles = appRoleDAO.findByUserId(appUser.getId()); // nếu người dùng có quyền customer, shipper
            // save new role if user has new role
            for (AppRole appRole : appUser.getAppRoles()) { // lấy được danh sách quyền mới. Vd: admin, shipper
                AtomicBoolean isNewRole = new AtomicBoolean(false);
                appRoles.forEach(role -> { // hiện có quyền customer, shipper trong danh sách quyền cũ
                    if (role.getId() != appRole.getId()) { // nếu có quyền mới: id admin != id còn lại
                        isNewRole.set(true);
                    }
                });

                if (isNewRole.get()) { // đúng nếu có quyền mới
                    appUserRoleDAO.save(appUser.getId(), appRole.getId()); // lưu quyền mới admin, trong database có customer, shipper và admin
                }

                isNewRole.set(false); // reset
            }

            // remove role if user role not exist
            for (AppRole appRole : appRoles) { // hiện tại đang có quyền customer, shipper (không lấy quyền admin vừa thêm)
                AtomicBoolean isRemove = new AtomicBoolean(false);
                appUser.getAppRoles().forEach(role -> { // hiện có quyền admin, shipper trong danh sách quyền mới
                    if (role.getId() != appRole.getId()) { // nếu không có quyền cũ: id customer != id còn lại
                        isRemove.set(true);
                    }
                });

                if (isRemove.get()) { // nếu có quyền không có trong appUser
                    appUserRoleDAO.remove(appUser.getId(), appRole.getId()); // remove customer
                }

                isRemove.set(false); // reset
            }
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
