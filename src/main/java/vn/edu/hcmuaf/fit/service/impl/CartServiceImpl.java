package vn.edu.hcmuaf.fit.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.dao.impl.*;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.cart.CartItemDto;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    private static final Logger logger = LogManager.getLogger(CartServiceImpl.class);
    private final CartDAO cartDAO;
    private final AppUserDAO appUserDAO;
    private final ProductDetailDAO productDetailDAO;

    public CartServiceImpl() {
        cartDAO = CartDAOImpl.getInstance();
        appUserDAO = AppUserDAOImpl.getInstance();
        productDetailDAO = ProductDetailDAOImpl.getInstance();

        ((CartDAOImpl) cartDAO).setAppUserDAO(appUserDAO);
        ((CartDAOImpl) cartDAO).setProductDetailDAO(productDetailDAO);
    }

    @Override
    public AppServiceResult<List<CartItemDto>> getCart(Long userId) {
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Tài khoản không tồn tại", null);
            }

            List<CartItem> cart = cartDAO.findAll(userId);
            List<CartItemDto> result = new ArrayList<>();

            cart.forEach(item -> result.add(CartItemDto.createFromEntity(item)));

            return new AppServiceResult<>(true, 0, "Tìm thấy giỏ hàng", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
                    "Không thể tải giỏ hàng", null);
        }
    }

    @Override
    public AppServiceResult<List<CartItemDto>> getCartForCheckout(Long userId, List<Long> productIds) {
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Tài khoản không tồn tại", null);
            }

            List<CartItem> cart = cartDAO.findAll(userId);
            List<CartItemDto> result = new ArrayList<>();

            for (CartItem item : cart) {
                if (productIds.contains(item.getProduct().getId())) {
                    if (item.getQuantity() > item.getProduct().getUnitInStock()) {
                        return new AppServiceResult<>(false, AppError.Validation.errorCode(),
                                "Số lượng sản phẩm trong giỏ hàng không đủ", null);
                    } else result.add(CartItemDto.createFromEntity(item));
                }
            }

            return new AppServiceResult<>(true, 0, "Tìm thấy sản phẩm trong giỏ hàng", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(),"Không thể tải giỏ hàng", null);
        }
    }

    @Override
    public AppServiceResult<CartItemDto> addToCart(Long userId, Long productId, Integer quantity) {
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),"Không tìm thấy tài khoản", null);
            }

            ProductDetail productDetail = productDetailDAO.findById(productId);
            if (productDetail == null) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),"Id sản phẩm không tồn tại: " + productId, null);
            }

            if (productDetail.getUnitInStock() < quantity) {
                return new AppServiceResult<>(false, AppError.Validation.errorCode(),"Số lượng sản phẩm không đủ: " + productDetail.getUnitInStock(), null);
            }

            CartItem cartItem = cartDAO.findById(userId, productId);
            if (cartItem == null) {
                cartItem = new CartItem(appUser, productDetail, quantity);
                cartDAO.save(cartItem, true);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartDAO.save(cartItem, false);
            }

            return new AppServiceResult<>(true, 0, "Thêm sản phẩm thành công", CartItemDto.createFromEntity(cartItem));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new AppServiceResult<>(false, AppError.Unknown.errorCode(), "Thêm sản phẩm không thành công", null);
        }
    }

    @Override
    public AppBaseResult updateQuantity(Long userId, Long productId, Integer quantity) {
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Không tìm thấy tài khoản");
            }

            ProductDetail productDetail = productDetailDAO.findById(productId);
            if (productDetail == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Id sản phẩm không tồn tại: " + productId);
            }

            CartItem cartItem = cartDAO.findById(userId, productId);
            if (cartItem == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Sản phẩm không có trong giỏ hàng");
            }

            if (productDetail.getUnitInStock() < quantity) {
                cartItem.setQuantity(productDetail.getUnitInStock());
                cartDAO.save(cartItem, false);

                return new AppServiceResult<>(false, AppError.Validation.errorCode(),"Số lượng sản phẩm không đủ: " + productDetail.getUnitInStock(), productDetail.getUnitInStock());
            }

            cartItem.setQuantity(quantity);

            cartDAO.save(cartItem, false);

            return new AppServiceResult<>(true, 0, "Cập nhật thành công", CartItemDto.createFromEntity(cartItem));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể cập nhật");
        }
    }

    @Override
    public AppBaseResult removeFromCart(Long userId, Long productId) {
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Không tìm thấy tài khoản");
            }

            ProductDetail productDetail = productDetailDAO.findById(productId);
            if (productDetail == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Id sản phẩm không tồn tại: " + productId);
            }

            cartDAO.remove(userId, productId);

            return new AppBaseResult(true, 0, "Xoá sản phẩm thành công");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể xoá sản phẩm");
        }
    }

    @Override
    public AppBaseResult removeAllFromCart(Long userId) {
        try {
            AppUser appUser = appUserDAO.findById(userId);
            if (appUser == null) {
                return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Không tìm thấy tài khoản");
            }

            cartDAO.removeAll(userId);

            return new AppBaseResult(true, 0, "Xoá sản phẩm thành công");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Không thể xoá tất cả sản phẩm");
        }
    }
}
