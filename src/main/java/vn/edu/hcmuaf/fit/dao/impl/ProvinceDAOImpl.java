package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.DistrictDAO;
import vn.edu.hcmuaf.fit.dao.ProvinceDAO;
import vn.edu.hcmuaf.fit.entity.District;
import vn.edu.hcmuaf.fit.entity.Province;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.*;

public class ProvinceDAOImpl implements ProvinceDAO {
    private static ProvinceDAO instance;
    private Connection connection;

    private DistrictDAO districtDAO;

    private ProvinceDAOImpl() {
    }

    public static ProvinceDAO getInstance() {
        if (instance == null) {
            instance = new ProvinceDAOImpl();
        }
        return instance;
    }

    public void setDistrictDAO(DistrictDAO districtDAO) {
        this.districtDAO = districtDAO;
    }

    @Override
    public List<Province> findAll() {
        List<Province> provinces = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.PROVINCE.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");
                Set<District> districts = new LinkedHashSet<>(districtDAO.findByProvinceId(id));
                Province province = new Province(id, name, prefix, districts);
                provinces.add(province);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return provinces;
    }

    @Override
    public Province findById(Long id) {
        Province province = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.PROVINCE.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");

                province = new Province(id, name, prefix, null);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return province;
    }

    @Override
    public void save(Province object) {
    }

    @Override
    public void remove(Long id) {
    }
}
