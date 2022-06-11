package vn.edu.hcmuaf.fit.service;


import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.product.*;

import java.util.List;

public interface ProductService {
	AppServiceResult<List<ProductDto>> getProducts();
    AppServiceResult<List<ProductDto>> getProducts(boolean sold);
	AppServiceResult<List<ProductDto>> getProductsByName(String name);
	AppServiceResult<List<ProductDto>> getProductsBySize(String size);
	AppServiceResult<List<ProductDto>> getProductsByCategoryId(Long categoryId);
	AppServiceResult<List<ProductDto>> getProductsByTrademark(Long trademarkId);
	AppServiceResult<List<ProductDto>> getProductsByStatus(boolean isActive);
	AppServiceResult<ProductDto> getProduct(Long id);
	AppServiceResult<ProductDto> createProduct(ProductCreate product);
	AppServiceResult<ProductDto> updateProduct(ProductUpdate product);
	AppServiceResult<ProductDto> updateStatus(ProductUpdate product);
	AppBaseResult deleteProduct(Long id);
}
