package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.domain.AppBaseResult;
import vn.edu.hcmuaf.fit.domain.AppServiceResult;
import vn.edu.hcmuaf.fit.dto.category.*;

import java.util.List;

public interface CategoryService {
    AppServiceResult<List<CategoryDto>> getCategories();
    AppServiceResult<CategoryDto> getCategory(Long id);
    AppServiceResult<CategoryDto> getCategoryBySku(String sku);
    AppServiceResult<CategoryDto> getCategoryByName(String name);
    AppBaseResult createCategory(CategoryCreate category);
    AppBaseResult updateCategory(CategoryUpdate category);
    AppBaseResult deleteCategory(Long id);
    AppBaseResult updateStatus(Long id, boolean active);
}
