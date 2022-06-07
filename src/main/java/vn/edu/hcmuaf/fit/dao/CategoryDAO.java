package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.Category;

public interface CategoryDAO extends BaseDAO<Category> {
	Category findBySku(String sku);
	Category findByName(String name);
}
