package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.ProductDAO;
import vn.edu.hcmuaf.fit.dao.impl.*;
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
		this.productDAO = ProductDAOImpl.getInstance();

		((ProductDAOImpl) productDAO).setTrademarkDAO(TrademarkDAOImpl.getInstance());
		((ProductDAOImpl) productDAO).setCategoryDAO(CategoryDAOImpl.getInstance());
		((ProductDAOImpl) productDAO).setProductDetailDAO(ProductDetailDAOImpl.getInstance());
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProducts() {
		try {
			List<Product> products = productDAO.findAll();
			List<ProductDto> result = new ArrayList<>();

			products.forEach(product -> result.add(ProductDto.createFromEntity(product)));

			System.out.println(result.size());

			return new AppServiceResult<>(true, 0, "Succeed!", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProducts(boolean sold) {
		try {
			List<Product> products = productDAO.findByStatus(sold);
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
	public AppServiceResult<List<ProductDto>> getProductsByName(String name) {
		return null;
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProductsBySize(String size) {
		return null;
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProductsByCategoryId(Long categoryId) {
		return null;
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProductsByTrademark(Long trademarkId) {
		return null;
	}

	@Override
	public AppServiceResult<List<ProductDto>> getProductsByStatus(boolean isActive) {
		return null;
	}

	@Override
	public AppServiceResult<ProductDto> getProduct(Long id) {
		try {
			Product product = productDAO.findById(id);
			if (product == null) {
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						AppError.Validation.errorMessage(), null);
			}
			return new AppServiceResult<>(true, 0, "Succeed!", ProductDto.createFromEntity(product));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
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
	public AppServiceResult<ProductDto> updateStatus(ProductUpdate product) {
		return null;
	}

	@Override
	public AppBaseResult deleteProduct(Long id) {
		return null;
	}
}
