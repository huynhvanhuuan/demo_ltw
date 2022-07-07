package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.cart.CartItemDto;

import java.util.List;

public interface CartService {
    AppServiceResult<List<CartItemDto>> getCart(Long userId);
    AppServiceResult<List<CartItemDto>> getCartForCheckout(Long userId, List<Long> productIds);
    AppServiceResult<CartItemDto> addToCart(Long userId, Long productId, Integer quantity);
    AppBaseResult updateQuantity(Long userId, Long productId, Integer quantity);
    AppBaseResult removeFromCart(Long userId, Long productId);
    AppBaseResult removeAllFromCart(Long userId);
}
