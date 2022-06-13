package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.MaterialDAO;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.entity.Material;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    private static MaterialDAOImpl instance;
    private final IConnectionPool connectionPool;
    private Connection connection;

    private MaterialDAOImpl() {
        this.connectionPool = DbManager.connectionPool;
    }

    public static MaterialDAOImpl getInstance() {
        if (instance == null) {
            instance = new MaterialDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.MATERIAL.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Material material = new Material(id, name);
                materials.add(material);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return materials;
    }

    @Override
    public Material findById(Long id) {
        Material material = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.MATERIAL.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String name = rs.getString("name");
                material = new Material(id, name);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return material;
    }

    @Override
    public void save(Material object) {

    }

    @Override
    public void remove(Long id) {

    }
}
