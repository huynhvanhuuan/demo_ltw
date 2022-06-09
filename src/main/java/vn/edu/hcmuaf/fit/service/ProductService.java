package vn.edu.hcmuaf.fit.service;


import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.product.*;

import java.util.List;

public interface ProductService {
	AppServiceResult<List<ProductDto>> getProducts();
	AppServiceResult<ProductDto> getProductByStatus(boolean sold);
	AppServiceResult<ProductDto> getProduct(int id);
	AppServiceResult<ProductDto> createProduct(ProductCreate item);
	AppServiceResult<ProductDto> updateProduct(ProductUpdate item);
	AppBaseResult deleteProduct(Long id);
	AppServiceResult<ProductDto> updateStatus(Long id);
}
