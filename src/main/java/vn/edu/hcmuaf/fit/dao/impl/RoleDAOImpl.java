package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.RoleDAO;
import vn.edu.hcmuaf.fit.entity.Role;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private static RoleDAOImpl instance;
    private Connection connection;

    private RoleDAOImpl() {
    }

    public static RoleDAOImpl getInstance() {
        if (instance == null) {
            instance = new RoleDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ROLE.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String suffix = rs.getString("suffix");

                Role role = new Role(id, name, suffix);
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
    public Role findById(Long id) {
        Role role = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ROLE.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long idRole = rs.getLong("id");
                String name = rs.getString("name");
                String suffix = rs.getString("suffix");

                role = new Role(idRole, name, suffix);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return role;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return role;
    }

    @Override
    public void save(Role object) {

    }

    @Override
    public void remove(Long id) {

    }
}
