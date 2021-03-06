package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAOImpl implements AddressDAO {
    private static AddressDAOImpl instance;
    private Connection connection;

    private DistrictDAO districtDAO;
    private WardDAO wardDAO;

    private AddressDAOImpl() {
        this.districtDAO = DistrictDAOImpl.getInstance();
        this.wardDAO = WardDAOImpl.getInstance();

        ((WardDAOImpl) wardDAO).setDistrictDAO(districtDAO);
    }

    public static AddressDAOImpl getInstance() {
        if (instance == null) {
            instance = new AddressDAOImpl();
        }
        return instance;
    }

    public void setDistrictDAO(DistrictDAO districtDAO) {
        this.districtDAO = districtDAO;
    }

    public void setWardDAO(WardDAO wardDAO) {
        this.wardDAO = wardDAO;
    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public Address findById(Long id) {
        Address address = null;
        connection = DbManager.connectionPool.getConnection();
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
                Boolean isDefault = rs.getBoolean("is_default");

                address = new Address(id, number, street, ward, district, path, isDefault);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return address;
    }

    @Override
    public void save(Address address) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(address.getId() == 0 ? QUERY.ADDRESS.CREATE : QUERY.ADDRESS.UPDATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getNumber());
            statement.setString(2, address.getStreet());
            statement.setLong(3, address.getWard() == null ? 0 : address.getWard().getId());
            statement.setLong(4, address.getDistrict().getId());
            statement.setString(5, address.getPath());

            if (address.getId() != 0) {
                statement.setBoolean(6, address.getDefaultAddress());
                statement.setLong(7, address.getId());
            }
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                address.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void remove(Long id) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public List<Address> findByTrademarkId(Long trademarkId) {
        List<Address> addresses = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
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
                Boolean isDefault = rs.getBoolean("is_default");

                Address address = new Address(id, number, street, ward, district, path, isDefault);
                addresses.add(address);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return addresses;
    }
    
    @Override
    public List<Address> findByUserId(Long userId) {
        List<Address> addresses = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
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
                Boolean isDefault = rs.getBoolean("is_default");

                Address address = new Address(id, number, street, ward, district, path, isDefault);
                addresses.add(address);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return addresses;
    }

    @Override
    public Address findByPath(String path) {
        Address address = null;
        connection = DbManager.connectionPool.getConnection();
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
                Boolean isDefault = rs.getBoolean("is_default");

                address = new Address(id, number, street, ward, district, path, isDefault);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return address;
    }

    @Override
    public void saveForTrademark(Address address, Long trademarkId) {
        save(address);
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.CREATE_TRADEMARK_ADDRESS);
            statement.setLong(1, trademarkId);
            statement.setLong(2, address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void saveForUser(Address address, Long userId) {
        save(address);
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ADDRESS.CREATE_USER_ADDRESS);
            statement.setLong(1, userId);
            statement.setLong(2, address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

}
