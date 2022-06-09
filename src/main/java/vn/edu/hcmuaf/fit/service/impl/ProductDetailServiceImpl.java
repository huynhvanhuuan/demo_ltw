package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.ProductDetailDAO;
import vn.edu.hcmuaf.fit.dao.impl.ProductDetailDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.productDetail.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.service.ProductDetailService;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailServiceImpl implements ProductDetailService {
	private final ProductDetailDAO productDetailDAO;
	
	public ProductDetailServiceImpl() {
		this.productDetailDAO = new ProductDetailDAOImpl();
	}
	
	@Override
	public AppServiceResult<List<ProductDetailDto>> getProductDetails(Product product) {
		try {
			List<ProductDetail> productDetails = product == null ? productDetailDAO.findAll() : productDetailDAO.findByProduct(product);
			List<ProductDetailDto> result = new ArrayList<>();

			productDetails.forEach(productDetail -> result.add(ProductDetailDto.createFromEntity(productDetail)));

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppServiceResult<ProductDetailDto> getProductDetail(Long id) {
		try {
			ProductDetail productDetail = productDetailDAO.findById(id);

			if (productDetail == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Product id is not exist: " + id, null);

			return new AppServiceResult<>(true, 0, "Succeed!", ProductDetailDto.createFromEntity(productDetail));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppServiceResult<ProductDetailDto> createProductDetail(ProductDetailCreate productDetail) {
		return null;
	}
	
	@Override
	public AppBaseResult updateProductDetail(ProductDetailUpdate productDetail) {
		return null;
	}
	
	@Override
	public AppBaseResult deleteProductDetail(Long id) {
		return null;
	}
	
	@Override
	public AppServiceResult<ProductDetailDto> updateStatus(Long id) {
		return null;
	}

}
