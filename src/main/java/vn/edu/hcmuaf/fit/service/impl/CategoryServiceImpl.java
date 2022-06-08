package vn.edu.hcmuaf.fit.service.impl;

import vn.edu.hcmuaf.fit.constant.AppError;
import vn.edu.hcmuaf.fit.dao.CategoryDAO;
import vn.edu.hcmuaf.fit.dao.impl.CategoryDAOImpl;
import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.category.*;
import vn.edu.hcmuaf.fit.entity.Category;
import vn.edu.hcmuaf.fit.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
	private final CategoryDAO categoryDAO;
	
	public CategoryServiceImpl() {
		this.categoryDAO = new CategoryDAOImpl();
	}
	
	@Override
	public AppServiceResult<List<CategoryDto>> getCategories() {
		try {
			List<Category> categories = categoryDAO.findAll();
			List<CategoryDto> result = new ArrayList<>();
			
			categories.forEach(category -> result.add(CategoryDto.createFromEntity(category)));
			
			return new AppServiceResult<>(true, 0, "Succeed!", result);
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
						"Category id is not exist: " + id, null);
			
			return new AppServiceResult<>(true, 0, "Succeed!", CategoryDto.createFromEntity(category));
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
			
			return new AppServiceResult<>(true, 0, "Succeed!", CategoryDto.createFromEntity(category));
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
			
			return new AppServiceResult<>(true, 0, "Succeed!", CategoryDto.createFromEntity(category));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppServiceResult<CategoryDto> createCategory(CategoryCreate category) {
		try {
			Category newCategory = new Category();
			
			if (category.getName() == null && category.getName().isEmpty()) {
				return new AppServiceResult<>(false, AppError.Validation.errorCode(),
						"Name is required!", null);
			}
			
			List<Category> categories = categoryDAO.findAll();
			for (Category c : categories) {
				if (c.getName().equals(category.getName())) {
					return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Name is exist!", null);
				}
			}
			
			newCategory.setId(0L);
			newCategory.setSku(category.getSku());
			newCategory.setName(category.getName());
			
			categoryDAO.save(newCategory);
			
			return new AppServiceResult<>(true, 0, "Succeed!", CategoryDto.createFromEntity(newCategory));
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
	
	@Override
	public AppBaseResult updateCategory(CategoryUpdate category) {
		try {
			Category updatedCategory = categoryDAO.findById(category.getId());
			
			if (updatedCategory == null)
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Category id is not exist: " + category.getId());
			
			if (category.getName() == null && category.getName().equals("")) {
				return new AppServiceResult<CategoryDto>(false, AppError.Validation.errorCode(),
						"Name is required!", null);
			}
			
			List<Category> categories = categoryDAO.findAll();
			for (Category c : categories) {
				if (c.getName().equals(category.getName()) && c.getId() != category.getId()) {
					return new AppServiceResult<CategoryDto>(false, AppError.Validation.errorCode(), "Name is exist!", null);
				}
			}
			
			updatedCategory.setSku(category.getSku());
			updatedCategory.setName(category.getName());
			updatedCategory.setActive(category.isActive());
			
			categoryDAO.save(updatedCategory);
			
			return AppBaseResult.GenarateIsSucceed();
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}
	
	@Override
	public AppBaseResult deleteCategory(Long id) {
		try {
			Category category = categoryDAO.findById(id);
			
			if (category != null) {
				categoryDAO.removeById(id);
				return AppBaseResult.GenarateIsSucceed();
			} else {
				return AppBaseResult.GenarateIsFailed(AppError.Validation.errorCode(), "Category id is not exist: " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return AppBaseResult.GenarateIsFailed(AppError.Unknown.errorCode(), AppError.Unknown.errorMessage());
		}
	}
	
	@Override
	public AppServiceResult<CategoryDto> updateStatus(Long id) {
		try {
			Category category = categoryDAO.findById(id);
			
			if (category != null) {
				category.setActive(!category.isActive());
				categoryDAO.save(category);
				return new AppServiceResult<>(true, 0, "Succeed!", CategoryDto.createFromEntity(category));
			} else
				return new AppServiceResult<>(false, AppError.Validation.errorCode(), "Category id is not exist: " + id, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new AppServiceResult<>(false, AppError.Unknown.errorCode(),
					AppError.Unknown.errorMessage(), null);
		}
	}
}
