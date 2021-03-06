package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.ProductDetail;

import java.util.List;

public interface ProductDetailDAO extends BaseDAO<ProductDetail> {
    List<ProductDetail> findByProductId(Long productId);
    ProductDetail findById(Long productId, Long colorId, Long materialId);
    ProductDetail findBySku(String sku);
}
