package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private final IConnectionPool connectionPool;
    private Connection connection;

    private final DistrictDAO districtDAO;
    private final WardDAO wardDAO;

    public AddressDAOImpl() {
        this.connectionPool = DbManager.connectionPool;
        this.districtDAO = new DistrictDAOImpl();
        this.wardDAO = new WardDAOImpl();
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Address findById(Long id) {
        Address address = null;
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String number = rs.getString("number");
                String street = rs.getString("street");
                Ward ward = wardDAO.findById(rs.getLong("ward_id"));
                District district = districtDAO.findById(rs.getLong("district_id"));
                String path = rs.getString("path");

                address = new Address(id, number, street, ward, district, path);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        connectionPool.releaseConnection(connection);
        return address;
    }

    @Override
    public void save(Address address) {
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.CREATE);
            statement.setString(1, address.getNumber());
            statement.setString(2, address.getStreet());
            statement.setLong(3, address.getWard() == null ? 0 : address.getWard().getId());
            statement.setLong(4, address.getDistrict().getId());
            statement.setString(5, address.getPath());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void removeById(Long id) {
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public List<Address> findByTrademarkId(Long trademarkId) {
        List<Address> addresses = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            connectionPool.releaseConnection(connection);
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.FIND_BY_TRADEMARK_ID);
            statement.setLong(1, trademarkId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String number = rs.getString("number");
                String street = rs.getString("street");
                Ward ward = wardDAO.findById(rs.getLong("ward_id"));
                District district = districtDAO.findById(rs.getLong("district_id"));
                String path = rs.getString("path");

                Address address = new Address(id, number, street, ward, district, path);
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return addresses;
        }
        return addresses;
    }
    
    @Override
    public List<Address> findByUserId(Long userId) {
        List<Address> addresses = new ArrayList<>();
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.FIND_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String number = rs.getString("number");
                String street = rs.getString("street");
                Ward ward = wardDAO.findById(rs.getLong("ward_id"));
                District district = districtDAO.findById(rs.getLong("district_id"));
                String path = rs.getString("path");

                Address address = new Address(id, number, street, ward, district, path);
                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return addresses;
        }
        connectionPool.releaseConnection(connection);
        return addresses;
    }

    @Override
    public Address findByPath(String path) {
        Address address = null;
        connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.FIND_BY_PATH);
            statement.setString(1, path);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                long id = rs.getLong("id");
                String number = rs.getString("number");
                String street = rs.getString("street");
                Ward ward = wardDAO.findById(rs.getLong("ward_id"));
                District district = districtDAO.findById(rs.getLong("district_id"));

                address = new Address(id, number, street, ward, district, path);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        connectionPool.releaseConnection(connection);
        return address;
    }

}
