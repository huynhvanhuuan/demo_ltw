package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.dao.CartDAO;
import vn.edu.hcmuaf.fit.dao.impl.CartDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.entity.CartItem;
import vn.edu.hcmuaf.fit.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    private CartDAO cartDAO;

    public CartServiceImpl() {
        cartDAO = CartDAOImpl.getInstance();
    }

    @Override
    public AppServiceResult<List<CartItem>> getCart(Long userId) {
        return null;
    }

    @Override
    public AppServiceResult<CartItem> addToCart(Long userId, Long productId, Integer quantity) {
        return null;
    }

    @Override
    public AppServiceResult<CartItem> updateCart(Long userId, Long productId, Integer quantity) {
        return null;
    }

    @Override
    public AppServiceResult<CartItem> removeFromCart(Long userId, Long productId) {
        return null;
    }

    @Override
    public AppServiceResult<CartItem> removeAllFromCart(Long userId) {
        return null;
    }
}
