package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.entity.CartItem;

import java.util.List;

public interface CartService {
    AppServiceResult<List<CartItem>> getCart(Long userId);
    AppServiceResult<CartItem> addToCart(Long userId, Long productId, Integer quantity);
    AppServiceResult<CartItem> updateCart(Long userId, Long productId, Integer quantity);
    AppServiceResult<CartItem> removeFromCart(Long userId, Long productId);
    AppServiceResult<CartItem> removeAllFromCart(Long userId);
}
