package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;
import vn.edu.hcmuaf.fit.util.DateUtil;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class OrderDAOImpl implements OrderDAO {
    private static OrderDAOImpl instance;
    private Connection connection;
    private AppUserDAO appUserDAO;
    private ProductDetailDAO productDetailDAO;

    private OrderDAOImpl() {
        appUserDAO = AppUserDAOImpl.getInstance();
        productDetailDAO = ProductDetailDAOImpl.getInstance();
    }

    public static OrderDAOImpl getInstance() {
        if (instance == null) {
            instance = new OrderDAOImpl();
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
    public List<Order> findAll() {
        connection = DbManager.connectionPool.getConnection();
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.FIND_ALL);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                UUID orderTrackingNumber = UUID.fromString(rs.getString("order_tracking_number"));
                AppUser appUser = appUserDAO.findById(rs.getLong("user_id"));
                String address = rs.getString("address");
                Long totalPrice = rs.getLong("total_price");
                Long shippingFee = rs.getLong("shipping_fee");
                Date dateCreated = DateUtil.toDatetime(rs.getString("created_at"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("updated_at"));
                Integer status = rs.getInt("status");
                Set<OrderItem> items = new LinkedHashSet<>(findOrderItemsByOrderId(id));

                Order order = new Order(id, orderTrackingNumber, appUser, address, totalPrice, shippingFee, dateCreated, lastUpdated, status, items);
                orders.add(order);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return orders;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return orders;
    }

    @Override
    public Order findById(Long id) {
        connection = DbManager.connectionPool.getConnection();
        Order order = null;
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                UUID orderTrackingNumber = UUID.fromString(rs.getString("order_tracking_number"));
                AppUser appUser = appUserDAO.findById(rs.getLong("user_id"));
                String address = rs.getString("address");
                Long totalPrice = rs.getLong("total_price");
                Long shippingFee = rs.getLong("shipping_fee");
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Integer status = rs.getInt("status");
                Set<OrderItem> items = new LinkedHashSet<>(findOrderItemsByOrderId(id));

                order = new Order(id, orderTrackingNumber, appUser, address, totalPrice, shippingFee, dateCreated, lastUpdated, status, items);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return order;
    }

    private List<OrderItem> findOrderItemsByOrderId(Long orderId) {
        connection = DbManager.connectionPool.getConnection();
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER_DETAIL.FIND_BY_ORDER_ID);
            statement.setLong(1, orderId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ProductDetail product = productDetailDAO.findById(rs.getLong("product_id"));
                Long unitPrice = rs.getLong("unit_price");
                int quantity = rs.getInt("quantity");

                OrderItem item = new OrderItem(null, product, unitPrice, quantity);
                orderItems.add(item);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return orderItems;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return orderItems;
    }

    @Override
    public void save(Order order) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getOrderTrackingNumber().toString());
            statement.setLong(2, order.getAppUser().getId());
            statement.setString(3, order.getAddress());
            statement.setLong(4, order.getTotalPrice());
            statement.setLong(5, order.getShippingFee());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getLong(1));

                for (OrderItem item : order.getItems()) {
                    statement = connection.prepareStatement(QUERY.ORDER_DETAIL.CREATE);
                    statement.setLong(1, order.getId());
                    statement.setLong(2, item.getProduct().getId());
                    statement.setLong(3, item.getUnitPrice());
                    statement.setInt(4, item.getQuantity());
                    statement.executeUpdate();
                }
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return;
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public void remove(Long id) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return;
        }
        DbManager.connectionPool.releaseConnection(connection);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        connection = DbManager.connectionPool.getConnection();
        List<Order> orders = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.FIND_BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                UUID orderTrackingNumber = UUID.fromString(rs.getString("order_tracking_number"));
                AppUser appUser = appUserDAO.findById(rs.getLong("user_id"));
                String address = rs.getString("address");
                Long totalPrice = rs.getLong("total_price");
                Long shippingFee = rs.getLong("shipping_fee");
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Integer status = rs.getInt("status");
                Set<OrderItem> items = new LinkedHashSet<>(findOrderItemsByOrderId(id));

                Order order = new Order(id, orderTrackingNumber, appUser, address, totalPrice, shippingFee, dateCreated, lastUpdated, status, items);
                orders.add(order);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return orders;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return orders;
    }

    @Override
    public Order findByOrderTrackingNumber(UUID orderTrackingNumber) {
        connection = DbManager.connectionPool.getConnection();
        Order order = null;
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.FIND_BY_ORDER_TRACKING_NUMBER);
            statement.setString(1, orderTrackingNumber.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                AppUser appUser = appUserDAO.findById(rs.getLong("user_id"));
                String address = rs.getString("address");
                Long totalPrice = rs.getLong("total_price");
                Long shippingFee = rs.getLong("shipping_fee");
                Date dateCreated = DateUtil.toDatetime(rs.getString("date_created"));
                Date lastUpdated = DateUtil.toDatetime(rs.getString("last_updated"));
                Integer status = rs.getInt("status");
                Set<OrderItem> items = new LinkedHashSet<>(findOrderItemsByOrderId(id));

                order = new Order(id, orderTrackingNumber, appUser, address, totalPrice, shippingFee, dateCreated, lastUpdated, status, items);
            }
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return null;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return order;
    }

    @Override
    public boolean updateStatus(Long orderId, Integer status) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.UPDATE_STATUS);
            statement.setInt(1, status);
            statement.setLong(2, orderId);
            statement.executeUpdate();
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return false;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return true;
    }

    @Override
    public boolean updateAddress(Long orderId, String address) {
        connection = DbManager.connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.ORDER.UPDATE_ADDRESS);
            statement.setString(1, address);
            statement.setLong(2, orderId);
            statement.executeUpdate();
        } catch (Exception e) {
            DbManager.connectionPool.releaseConnection(connection);
            return false;
        }
        DbManager.connectionPool.releaseConnection(connection);
        return true;
    }
}
