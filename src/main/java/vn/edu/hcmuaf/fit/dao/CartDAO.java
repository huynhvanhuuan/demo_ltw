package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.CartItem;

import java.util.List;

public interface CartDAO extends BaseDAO<CartItem> {
    List<CartItem> findAll(Long userId);
    CartItem findById(Long userId, Long productId);
    void save(CartItem item, boolean isCreate);
    void remove(Long userId, Long productId);
    void removeAll(Long userId);
}
