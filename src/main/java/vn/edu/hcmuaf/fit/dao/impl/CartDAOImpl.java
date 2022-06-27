package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.dao.CartDAO;
import vn.edu.hcmuaf.fit.entity.Cart;

import java.sql.Connection;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static CartDAOImpl instance;
    private Connection connection;

    private CartDAOImpl() {
    }

    public static CartDAOImpl getInstance() {
        if (instance == null) {
            instance = new CartDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public void save(Cart object) {

    }

    @Override
    public void remove(Long id) {

    }
}
