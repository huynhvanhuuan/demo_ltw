package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.ProvinceDAO;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.entity.Province;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinceDAOImpl implements ProvinceDAO {
    private final IConnectionPool connectionPool;
    private Connection connection;

    public ProvinceDAOImpl() {
        this.connectionPool = DbManager.connectionPool;
    }

    @Override
    public List<Province> findAll() {
        List<Province> provinces = new ArrayList<>();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.PROVINCE.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String prefix = rs.getString("prefix");
                String name = rs.getString("name");

                Province province = new Province(id, prefix, name, null);
                provinces.add(province);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return null;
        }
        connectionPool.releaseConnection(connection);
        return provinces;
    }

    @Override
    public Province findById(Long id) {
        Province province = null;
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.PROVINCE.FIND_BY_ID);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String prefix = rs.getString("prefix");
                String name = rs.getString("name");

                province = new Province(id, prefix, name, null);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return null;
        }
        connectionPool.releaseConnection(connection);
        return province;
    }

    @Override
    public void save(Province object) {
    }

    @Override
    public void remove(Long id) {
    }
}
