package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.DistrictDAO;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.entity.District;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DistrictDAOImpl implements DistrictDAO {
    private final IConnectionPool connectionPool;
    private Connection connection;

    public DistrictDAOImpl() {
        this.connectionPool = DbManager.connectionPool;
    }

    @Override
    public List<District> findAll() {
        List<District> districts = new ArrayList<>();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.DISTRICT.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");

                District district = new District(id, name, prefix, null, null);
                districts.add(district);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return districts;
        }
        connectionPool.releaseConnection(connection);
        return districts;
    }

    @Override
    public District findById(Long id) {
        District district = null;
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.DISTRICT.FIND_BY_ID);
            ResultSet rs = statement.executeQuery();

            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;

            if (rs.next()) {
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");

                district = new District(id, name, prefix, null, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return district;
    }

    @Override
    public void save(District object) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<District> findByProvinceId(Long provinceId) {
        List<District> districts = new ArrayList<>();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.DISTRICT.FIND_BY_PROVINCE_ID);
            statement.setLong(1, provinceId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");

                District district = new District(id, name, prefix, null, null);
                districts.add(district);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return districts;
        }
        connectionPool.releaseConnection(connection);
        return districts;
    }
}
