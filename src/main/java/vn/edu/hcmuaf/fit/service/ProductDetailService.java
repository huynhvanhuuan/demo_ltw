package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.productDetail.*;
import vn.edu.hcmuaf.fit.entity.*;

import java.util.List;

public interface ProductDetailService {
	AppServiceResult<List<ProductDetailDto>> getProductDetails(Product product);
	AppServiceResult<ProductDetailDto> getProductDetail(Long id);
	AppServiceResult<ProductDetailDto> createProductDetail(ProductDetailCreate productDetail);
	AppBaseResult updateProductDetail(ProductDetailUpdate productDetail);
	AppBaseResult deleteProductDetail(Long id);
	AppServiceResult<ProductDetailDto> updateStatus(Long id);
}
