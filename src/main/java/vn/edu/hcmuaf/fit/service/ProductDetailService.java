package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.productDetail.*;

import java.util.List;

public interface ProductDetailService {
	AppServiceResult<List<ProductDetailDto>> getProductDetails(Long productId);
	AppServiceResult<ProductDetailDto> getProductDetail(Long id);
	AppServiceResult<ProductDetailDto> getProductDetail(Long productId, Long materialId, Long colorId);
	AppServiceResult<ProductDetailDto> createProductDetail(ProductDetailCreate productDetail);
	AppBaseResult updateProductDetail(ProductDetailUpdate productDetail);
	AppBaseResult updateQuantity(Long productId, Integer quantity, boolean isIncrease);
	AppServiceResult<ProductDetailDto> updateStatus(Long id);
}
