package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.database.IConnectionPool;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.*;

public class DistrictDAOImpl implements DistrictDAO {
    private final IConnectionPool connectionPool;
    private Connection connection;

    private final ProvinceDAO provinceDAO;
    private final WardDAO wardDAO;

    public DistrictDAOImpl() {
        this.connectionPool = DbManager.connectionPool;
        this.provinceDAO = new ProvinceDAOImpl();
        this.wardDAO = new WardDAOImpl();
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
                Province province = provinceDAO.findById(rs.getLong("province_id"));
                Set<Ward> wards = new HashSet<>(wardDAO.findByDistrictId(id));

                District district = new District(id, name, prefix, province, wards);
                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return districts;
        }
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
                Province province = provinceDAO.findById(rs.getLong("province_id"));
                Set<Ward> wards = new HashSet<>(wardDAO.findByDistrictId(id));

                district = new District(id, name, prefix, province, wards);
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
    public List<District> findByProvinceId(long provinceId) {
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
                Province province = provinceDAO.findById(provinceId);
                Set<Ward> wards = new HashSet<>(wardDAO.findByDistrictId(id));

                District district = new District(id, name, prefix, province, wards);
                districts.add(district);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return districts;
        }
        return districts;
    }
}
