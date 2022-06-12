package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.DistrictDAO;
import vn.edu.hcmuaf.fit.dao.WardDAO;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.entity.District;
import vn.edu.hcmuaf.fit.entity.Ward;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WardDAOImpl implements WardDAO {
    private static WardDAOImpl instance;
    private final IConnectionPool connectionPool;
    private Connection connection;

    private DistrictDAO districtDAO;

    private WardDAOImpl() {
        this.connectionPool = DbManager.connectionPool;
    }

    public static WardDAOImpl getInstance() {
        if (instance == null) {
            instance = new WardDAOImpl();
        }
        return instance;
    }

    public void setDistrictDAO(DistrictDAO districtDAO) {
        this.districtDAO = districtDAO;
    }

    @Override
    public List<Ward> findAll() {
        List<Ward> wards = new ArrayList<>();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.WARD.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");
                District district = districtDAO.findById(rs.getLong("district_id"));

                Ward ward = new Ward(id, name, prefix, district);
                wards.add(ward);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return null;
        }
        connectionPool.releaseConnection(connection);
        return wards;
    }

    @Override
    public Ward findById(Long id) {
        Ward ward = null;
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.WARD.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");
                District district = districtDAO.findById(rs.getLong("district_id"));

                ward = new Ward(id, name, prefix, district);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return null;
        }
        connectionPool.releaseConnection(connection);
        return ward;
    }

    @Override
    public void save(Ward object) {}

    @Override
    public void remove(Long id) {}

    @Override
    public List<Ward> findByDistrictId(Long districtId) {
        District district = districtDAO.findById(districtId);
        List<Ward> wards = new ArrayList<>();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.WARD.FIND_BY_DISTRICT_ID);
            statement.setLong(1, districtId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");

                Ward ward = new Ward(id, name, prefix, district);
                wards.add(ward);
            }
        } catch (SQLException e) {
            connectionPool.releaseConnection(connection);
            return null;
        }
        connectionPool.releaseConnection(connection);
        return wards;
    }
}
