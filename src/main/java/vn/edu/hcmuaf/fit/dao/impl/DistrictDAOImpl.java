package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.*;

public class DistrictDAOImpl implements DistrictDAO {
    private static DistrictDAO instance;
    private Connection connection;

    private ProvinceDAO provinceDAO;
    private WardDAO wardDAO;

    private DistrictDAOImpl() {
        this.wardDAO = WardDAOImpl.getInstance();

        ((WardDAOImpl) wardDAO).setDistrictDAO(this);
    }

    public static DistrictDAO getInstance() {
        if (instance == null) {
            instance = new DistrictDAOImpl();
        }
        return instance;
    }

    public void setProvinceDAO(ProvinceDAO provinceDAO) {
        this.provinceDAO = provinceDAO;
    }

    public void setWardDAO(WardDAO wardDAO) {
        this.wardDAO = wardDAO;
    }

    @Override
    public List<District> findAll() {
        return null;
    }

    @Override
    public District findById(Long id) {
        District district = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.DISTRICT.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");
                Province province = provinceDAO.findById(rs.getLong("province_id"));

                district = new District(id, name, prefix, province, null);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return district;
    }

    @Override
    public void save(District district) {
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<District> findByProvinceId(Long provinceId) {
        Province province = provinceDAO.findById(provinceId);
        List<District> districts = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.DISTRICT.FIND_BY_PROVINCE_ID);
            statement.setLong(1, provinceId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String prefix = rs.getString("prefix");
                Set<Ward> wards = new LinkedHashSet<>(wardDAO.findByDistrictId(id));

                District district = new District(id, name, prefix, province, wards);
                districts.add(district);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return districts;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return districts;
    }
}
