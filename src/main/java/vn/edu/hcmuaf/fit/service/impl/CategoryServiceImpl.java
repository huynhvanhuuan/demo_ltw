package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.CategoryDAO;
import vn.edu.hcmuaf.fit.dao.ProductDAO;
import vn.edu.hcmuaf.fit.dao.impl.CategoryDAOImpl;
import vn.edu.hcmuaf.fit.dao.impl.ProductDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.category.*;
import vn.edu.hcmuaf.fit.entity.Category;
import vn.edu.hcmuaf.fit.entity.Product;
import vn.edu.hcmuaf.fit.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
	private final CategoryDAO categoryDAO;
	private final ProductDAO productDAO;

	public CategoryServiceImpl() {
		categoryDAO = CategoryDAOImpl.getInstance();
		productDAO = ProductDAOImpl.getInstance();
	}

	@Override
	public AppServiceResult<List<CategoryDto>> getCategories() {
		try {
			List<Category> categories = categoryDAO.findAll();
			List<CategoryDto> result = new ArrayList<>();
			
			categories.forEach(category -> result.add(CategoryDto.createFromEntity(category)));
			
			return new AppServiceResult<>(true, 0, "Success", result);
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppServiceResult<CategoryDto> getCategory(Long id) {
		try {
			Category category = categoryDAO.findById(id);
			
			if (category == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Thể loại không tồn tại", null);
			
			return new AppServiceResult<>(true, 0, "Success", CategoryDto.createFromEntity(category));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppServiceResult<CategoryDto> getCategoryBySku(String sku) {
		try {
			Category category = categoryDAO.findBySku(sku);
			
			if (category == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Category sku is not exist: " + sku, null);
			
			return new AppServiceResult<>(true, 0, "Success", CategoryDto.createFromEntity(category));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppServiceResult<CategoryDto> getCategoryByName(String name) {
		try {
			Category category = categoryDAO.findByName(name);
			
			if (category == null)
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Category name is not exist: " + name, null);
			
			return new AppServiceResult<>(true, 0, "Success", CategoryDto.createFromEntity(category));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					"Không thể tải danh sách thể loại", null);
		}
	}
	
	@Override
	public AppBaseResult createCategory(CategoryCreate category) {
		try {
			Category newCategory = new Category();
			
			if (category.getName() == null && category.getName().isEmpty()) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Tên thể loại không được để trống!");
			}
			
			List<Category> categories = categoryDAO.findAll();
			for (Category c : categories) {
				if (c.getName().equals(category.getName())) {
					return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(),"Tên thể loại đã tồn tại!");
				}
			}
			
			newCategory.setId(0L);
			newCategory.setSku(category.getSku());
			newCategory.setName(category.getName());
			
			categoryDAO.save(newCategory);
			
			return new AppBaseResult(true, 0, "Tạo loại sản phẩm thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Tạo loại sản phẩm thất bại!");
		}
	}
	
	@Override
	public AppBaseResult updateCategory(CategoryUpdate category) {
		try {
			Category updateCategory = categoryDAO.findById(category.getId());
			
			if (updateCategory == null)
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Thể loại không tồn tại!");
			
			if (category.getName() == null && category.getName().equals("")) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Tên thể loại không được để trống!");
			}
			
			List<Category> categories = categoryDAO.findAll();
			for (Category c : categories) {
				if (c.getName().equals(category.getName()) && c.getId() != category.getId()) {
					return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Tên thể loại đã tồn tại!");
				}
			}

			updateCategory.setSku(category.getSku());
			updateCategory.setName(category.getName());

			categoryDAO.save(updateCategory);
			
			return new AppBaseResult(true, 0, "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Cập nhật thất bại!");
		}
	}

	@Override
	public AppBaseResult updateStatus(Long id, boolean active) {
		try {
			Category updateCategory = categoryDAO.findById(id);

			if (updateCategory == null)
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Thể loại không tồn tại!");

			updateCategory.setActive(active);

			categoryDAO.save(updateCategory);

			return new AppBaseResult(true, 0, "Cập nhật thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Cập nhật thất bại!");
		}
	}
	
	@Override
	public AppBaseResult deleteCategory(Long id) {
		try {
			Category category = categoryDAO.findById(id);
			if (category == null) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Thể loại không tồn tại!");
			}

			List<Product> products = productDAO.findByCategoryId(id);
			if (!products.isEmpty()) {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Thể loại đang được sử dụng: " + category.getName());
			}

			categoryDAO.remove(id);

			return new AppBaseResult(true, 0, "Xóa thể loại thành công!");
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), "Xóa thể loại thất bại!");
		}
	}
}
