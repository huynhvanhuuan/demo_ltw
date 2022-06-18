package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.RoleDAO;
import vn.edu.hcmuaf.fit.dao.UserDAO;
import vn.edu.hcmuaf.fit.entity.Role;
import vn.edu.hcmuaf.fit.entity.User;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;
    private Connection connection;
    private RoleDAO roleDAO;

    private UserDAOImpl() {
        roleDAO = RoleDAOImpl.getInstance();
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.USER.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String fullName = rs.getString("full_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_of_birth"));;
                boolean isMale = rs.getBoolean("is_male");
                String imageUrl = rs.getString("image_url");
                Date dateCreated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_created"));
                Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("last_updated"));
                boolean isVerified = rs.getBoolean("is_verified");
                Role role = roleDAO.findById(rs.getLong("role_id"));
                boolean isNotLocked = rs.getBoolean("is_not_locked");
                boolean active = rs.getBoolean("active");

                User user = new User(id, lastName, firstName, fullName, password, email, phone, dateOfBirth, isMale, imageUrl, dateCreated, lastUpdated, isVerified, role, isNotLocked, active, null, null, null);
                users.add(user);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return users;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.USER.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String fullName = rs.getString("full_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_of_birth"));;
                boolean isMale = rs.getBoolean("is_male");
                String imageUrl = rs.getString("image_url");
                Date dateCreated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_created"));
                Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("last_updated"));
                boolean isVerified = rs.getBoolean("is_verified");
                Role role = roleDAO.findById(rs.getLong("role_id"));
                boolean isNotLocked = rs.getBoolean("is_not_locked");
                boolean active = rs.getBoolean("active");

                user = new User(id, lastName, firstName, fullName, password, email, phone, dateOfBirth, isMale, imageUrl, dateCreated, lastUpdated, isVerified, role, isNotLocked, active, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return user;
    }

    @Override
    public void save(User user) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(user.getId() == 0 ? QUERY.USER.CREATE : QUERY.USER.UPDATE);
            statement.setString(1, user.getLastName());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setString(7, new SimpleDateFormat("yyyy-MM-dd").format(user.getDateOfBirth()));
            statement.setBoolean(8, user.isMale());
            statement.setString(9, user.getImageUrl());
            statement.setString(10, new SimpleDateFormat("yyyy-MM-dd").format(user.getDateCreated()));
            statement.setString(11, new SimpleDateFormat("yyyy-MM-dd").format(user.getLastUpdated()));
            statement.setBoolean(12, user.isVerified());
            statement.setLong(13, user.getRole().getId());
            statement.setBoolean(14, user.isNotLocked());
            statement.setBoolean(15, user.isActive());

            if (user.getId() != 0) {
                statement.setLong(16, user.getId());
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
    public User findByEmail(String email) {
        User user = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.USER.FIND_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String fullName = rs.getString("full_name");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_of_birth"));
                ;
                boolean isMale = rs.getBoolean("is_male");
                String imageUrl = rs.getString("image_url");
                Date dateCreated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_created"));
                Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("last_updated"));
                boolean isVerified = rs.getBoolean("is_verified");
                Role role = roleDAO.findById(rs.getLong("role_id"));
                boolean isNotLocked = rs.getBoolean("is_not_locked");
                boolean active = rs.getBoolean("active");

                user = new User(id, lastName, firstName, fullName, password, email, phone, dateOfBirth, isMale, imageUrl, dateCreated, lastUpdated, isVerified, role, isNotLocked, active, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        User user = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.USER.FIND_BY_PHONE);
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String fullName = rs.getString("full_name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_of_birth"));
                boolean isMale = rs.getBoolean("is_male");
                String imageUrl = rs.getString("image_url");
                Date dateCreated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("date_created"));
                Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("last_updated"));
                boolean isVerified = rs.getBoolean("is_verified");
                Role role = roleDAO.findById(rs.getLong("role_id"));
                boolean isNotLocked = rs.getBoolean("is_not_locked");
                boolean active = rs.getBoolean("active");

                user = new User(id, lastName, firstName, fullName, password, email, phone, dateOfBirth, isMale, imageUrl, dateCreated, lastUpdated, isVerified, role, isNotLocked, active, null, null, null);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return user;
    }
}
