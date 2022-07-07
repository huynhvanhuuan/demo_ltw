package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static CartDAOImpl instance;
    private Connection connection;
    private AppUserDAO appUserDAO;
    private ProductDetailDAO productDetailDAO;

    private CartDAOImpl() {
    }

    public static CartDAOImpl getInstance() {
        if (instance == null) {
            instance = new CartDAOImpl();
        }
        return instance;
    }

    public void setAppUserDAO(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    public void setProductDetailDAO(ProductDetailDAO productDetailDAO) {
        this.productDetailDAO = productDetailDAO;
    }

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public CartItem findById(Long id) {
        return null;
    }

    @Override
    public void save(CartItem item) {
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<CartItem> findAll(Long userId) {
        AppUser appUser = appUserDAO.findById(userId);
        List<CartItem> cart = new ArrayList<>();
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.CART.FIND_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ProductDetail productDetail = productDetailDAO.findById(rs.getLong("product_id"));
                int quantity = rs.getInt("quantity");

                CartItem item = new CartItem(appUser, productDetail, quantity);
                cart.add(item);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return cart;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return cart;
    }

    @Override
    public CartItem findById(Long userId, Long productId) {
        AppUser appUser = appUserDAO.findById(userId);
        ProductDetail productDetail = productDetailDAO.findById(productId);
        CartItem cartItem = null;
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.CART.FIND_BY_ID);
            statement.setLong(1, userId);
            statement.setLong(2, productId);
            ResultSet rs = statement.executeQuery();
            if (!rs.isBeforeFirst() && rs.getRow() == 0) return null;
            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                cartItem = new CartItem(appUser, productDetail, quantity);
            }
        } catch (SQLException e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return cartItem;
    }

    @Override
    public void save(CartItem item, boolean isCreate) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement;
            if (isCreate) {
                statement = connection.prepareStatement(QUERY.CART.ADD_TO_CART);
                statement.setLong(1, item.getAppUser().getId());
                statement.setLong(2, item.getProduct().getId());
                statement.setInt(3, item.getQuantity());
            } else {
                statement = connection.prepareStatement(QUERY.CART.UPDATE_QUANTITY);
                statement.setInt(1, item.getQuantity());
                statement.setLong(2, item.getAppUser().getId());
                statement.setLong(3, item.getProduct().getId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void remove(Long userId, Long productId) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.CART.REMOVE);
            statement.setLong(1, userId);
            statement.setLong(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void removeAll(Long userId) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.CART.REMOVE_ALL);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbManager.connectionPool.releaseConnection(connection);
    }
}
