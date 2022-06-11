package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.ProductDAO;
import vn.edu.hcmuaf.fit.dao.impl.ProductDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.product.*;
import vn.edu.hcmuaf.fit.entity.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
	private final ProductDAO productDAO;
	
	public ProductServiceImpl() {
		this.productDAO = new ProductDAOImpl();
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProducts() {
		try {
			List<Product> products = productDAO.findAll();
			List<ProductDto> result = new ArrayList<>();

			products.forEach(product -> result.add(ProductDto.createFromEntity(product)));

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<ProductDto> getProductByStatus(boolean sold) {
		return null;
	}

	@Override
	public AppServiceResult<ProductDto> getProduct(Long id) {
		return null;
	}

	@Override
	public AppServiceResult<ProductDto> createProduct(ProductCreate item) {
		return null;
	}

	@Override
	public AppServiceResult<ProductDto> updateProduct(ProductUpdate item) {
		return null;
	}

	@Override
	public AppBaseResult deleteProduct(Long id) {
		return null;
	}

	@Override
	public AppServiceResult<ProductDto> updateStatus(Long id) {
		return null;
	}
}
