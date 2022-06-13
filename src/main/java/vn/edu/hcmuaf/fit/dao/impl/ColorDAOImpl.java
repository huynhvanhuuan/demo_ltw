package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.ColorDAO;
import vn.edu.hcmuaf.fit.entity.Color;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColorDAOImpl implements ColorDAO {
    private static ColorDAOImpl instance;
    private Connection connection;

    private ColorDAOImpl() {}

    public static ColorDAOImpl getInstance() {
        if (instance == null) {
            instance = new ColorDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            ResultSet rs = connection.prepareStatement(QUERY.COLOR.FIND_ALL).executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String hex = rs.getString("hex");
                Color color = new Color(id, name, hex);
                colors.add(color);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return colors;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return colors;
    }

    @Override
    public Color findById(Long id) {
        Color color = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.COLOR.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                String name = rs.getString("name");
                String hex = rs.getString("hex");
                color = new Color(id, name, hex);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return color;
    }

    @Override
    public void save(Color object) {

    }

    @Override
    public void remove(Long id) {

    }
}
