package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.ProductDetailDAO;
import vn.edu.hcmuaf.fit.dao.impl.*;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.productDetail.*;
import vn.edu.hcmuaf.fit.entity.ProductDetail;
import vn.edu.hcmuaf.fit.service.ProductDetailService;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailServiceImpl implements ProductDetailService {
	private final ProductDetailDAO productDetailDAO;
	public ProductDetailServiceImpl() {
		this.productDetailDAO = ProductDetailDAOImpl.getInstance();

		((ProductDetailDAOImpl) productDetailDAO).setProductDAO(ProductDAOImpl.getInstance());
		((ProductDetailDAOImpl) productDetailDAO).setColorDAO(ColorDAOImpl.getInstance());
		((ProductDetailDAOImpl) productDetailDAO).setMaterialDAO(MaterialDAOImpl.getInstance());
	}

	@Override
	public AppServiceResult<List<ProductDetailDto>> getProductDetails(Long productId) {
		try {
			List<ProductDetail> productDetails = productId == 0L ? productDetailDAO.findAll() : productDetailDAO.findByProductId(productId);
			List<ProductDetailDto> result = new ArrayList<>();

			productDetails.forEach(productDetail -> result.add(ProductDetailDto.createFromEntity(productDetail)));

			return new AppServiceResult<>(true, 0, "Success", result);
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

			return new AppServiceResult<>(true, 0, "Success", ProductDetailDto.createFromEntity(productDetail));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<ProductDetailDto> getProductDetail(Long productId, Long colorId, Long materialId) {
		ProductDetail productDetail = productDetailDAO.findById(productId, colorId, materialId);

		if (productDetail == null)
			return new AppServiceResult<>(false, AppError.Validation.errorCode(),
					"S???n ph???m kh??ng t???n t???i", null);

		return new AppServiceResult<>(true, 0, "T??m th???y s???n ph???m", ProductDetailDto.createFromEntity(productDetail));
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
	public AppBaseResult updateQuantity(Long productId, Integer quantity, boolean isIncrease) {
		ProductDetail productDetail = productDetailDAO.findById(productId);
		int unitInStock = productDetail.getUnitInStock();

		int newUnitInStock = isIncrease ? unitInStock + quantity : unitInStock - quantity;
		productDetail.setUnitInStock(newUnitInStock);

		productDetailDAO.save(productDetail);

		return new AppBaseResult(true, 0, "C???p nh???t s??? l?????ng th??nh c??ng");
	}

	@Override
	public AppServiceResult<ProductDetailDto> updateStatus(Long id) {
		return null;
	}

}
