package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.Product;

import java.util.List;

public interface ProductDAO extends BaseDAO<Product> {
    List<Product> findByStatus(boolean sold);
}
