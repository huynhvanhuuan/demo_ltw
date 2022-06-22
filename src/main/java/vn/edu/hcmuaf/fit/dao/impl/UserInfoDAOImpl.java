package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.UserInfoDAO;
import vn.edu.hcmuaf.fit.entity.UserInfo;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;
import vn.edu.hcmuaf.fit.util.DateUtil;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class UserInfoDAOImpl implements UserInfoDAO {
    private static UserInfoDAOImpl instance;
    private Connection connection;

    private UserInfoDAOImpl() {
    }

    public static UserInfoDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserInfoDAOImpl();
        }
        return instance;
    }

    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> users = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.USER_INFO.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String fullName = rs.getString("full_name");
                Date dateOfBirth = DateUtil.toDate(rs.getString("date_of_birth"));
                Boolean isMale = rs.getBoolean("is_male");
                String imageUrl = rs.getString("image_url");
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));

                UserInfo user = new UserInfo(id, lastName, firstName, fullName, dateOfBirth, isMale, imageUrl, dateCreated, lastUpdated);
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
    public UserInfo findById(Long id) {
        UserInfo user = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.USER_INFO.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            while (rs.next()) {
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String fullName = rs.getString("full_name");
                Date dateOfBirth = DateUtil.toDate(rs.getString("date_of_birth"));
                Boolean isMale = rs.getBoolean("is_male");
                String imageUrl = rs.getString("image_url");
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));

                user = new UserInfo(id, lastName, firstName, fullName, dateOfBirth, isMale, imageUrl, dateCreated, lastUpdated);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return user;
    }

    @Override
    public void save(UserInfo userInfo) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(userInfo.getId() == 0 ? QUERY.USER_INFO.CREATE : QUERY.USER_INFO.UPDATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userInfo.getLastName());
            statement.setString(2, userInfo.getFirstName());
            statement.setString(3, userInfo.getFullName());
            statement.setBoolean(4, userInfo.isMale());
            statement.setString(5, userInfo.getImageUrl());

            if (userInfo.getId() != 0) {
                statement.setString(6, DateUtil.toStringDate(userInfo.getDateOfBirth()));
                statement.setLong(7, userInfo.getId());
            }
            statement.executeUpdate();

            // set id after insert
            if (userInfo.getId() == 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    userInfo.setId(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void remove(Long id) {

    }
}
