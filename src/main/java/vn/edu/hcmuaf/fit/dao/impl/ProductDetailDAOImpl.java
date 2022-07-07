package vn.edu.hcmuaf.fit.dao.impl;

import vn.edu.hcmuaf.fit.constant.QUERY;
import vn.edu.hcmuaf.fit.dao.*;
import vn.edu.hcmuaf.fit.entity.*;
import vn.edu.hcmuaf.fit.infrastructure.DbManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class ProductDetailDAOImpl implements ProductDetailDAO {
	private static ProductDetailDAOImpl instance;
	private Connection connection;

	private ProductDAO productDAO;
	private ColorDAO colorDAO;
	private MaterialDAO materialDAO;
	
	private ProductDetailDAOImpl() {
	}

	public static ProductDetailDAOImpl getInstance() {
		if (instance == null) {
			instance = new ProductDetailDAOImpl();
		}
		return instance;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public void setColorDAO(ColorDAO colorDAO) {
		this.colorDAO = colorDAO;
	}

	public void setMaterialDAO(MaterialDAO materialDAO) {
		this.materialDAO = materialDAO;
	}

	@Override
	public List<ProductDetail> findAll() {
		List<ProductDetail> productDetailList = new ArrayList<>();
		try {
			connection = DbManager.connectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(QUERY.PRODUCT_DETAIL.FIND_ALL);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("id");
				String sku = rs.getString("sku");
				Product product = productDAO.findById(rs.getLong("product_id"));
				Color color = colorDAO.findById(rs.getLong("color_id"));
				Material material = materialDAO.findById(rs.getLong("material_id"));
				String imageUrl = rs.getString("image_url");
				long unitPrice = rs.getLong("unit_price");
				int unitInStock = rs.getInt("unit_in_stock");
				int discount = rs.getInt("discount");
				Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("date_created"));
				Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("last_updated"));
				boolean active = rs.getBoolean("active");
				ProductDetail productDetail = new ProductDetail(id, sku, product, color, material, imageUrl, unitPrice, unitInStock, discount, dateCreated, lastUpdated, active);
				productDetailList.add(productDetail);
			}
		} catch (Exception e) {
			DbManager.connectionPool.releaseConnection(connection);
			return productDetailList;
		}
		DbManager.connectionPool.releaseConnection(connection);
		return productDetailList;
	}

	@Override
	public ProductDetail findById(Long id) {
		ProductDetail productDetail = null;
		connection = DbManager.connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY.PRODUCT_DETAIL.FIND_BY_ID);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				String sku = rs.getString("sku");
				Product product = productDAO.findById(rs.getLong("product_id"));
				Color color = colorDAO.findById(rs.getLong("color_id"));
				Material material = materialDAO.findById(rs.getLong("material_id"));
				String imageUrl = rs.getString("image_url");
				long unitPrice = rs.getLong("unit_price");
				int unitInStock = rs.getInt("unit_in_stock");
				int discount = rs.getInt("discount");
				Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("date_created"));
				Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("last_updated"));
				boolean active = rs.getBoolean("active");
				productDetail = new ProductDetail(id, sku, product, color, material, imageUrl, unitPrice, unitInStock, discount, dateCreated, lastUpdated, active);
			}
		} catch (Exception e) {
			DbManager.connectionPool.releaseConnection(connection);
			return null;
		}
		DbManager.connectionPool.releaseConnection(connection);
		return productDetail;
	}

	@Override
	public void save(ProductDetail productDetail) {
		connection = DbManager.connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(productDetail.getId() == 0 ? QUERY.PRODUCT_DETAIL.CREATE : QUERY.PRODUCT_DETAIL.UPDATE);
			statement.setString(1, productDetail.getSku());
			statement.setLong(2, productDetail.getProduct().getId());
			statement.setLong(3, productDetail.getColor().getId());
			statement.setLong(4, productDetail.getMaterial().getId());
			statement.setString(5, productDetail.getImageUrl());
			statement.setLong(6, productDetail.getUnitPrice());
			statement.setInt(7, productDetail.getUnitInStock());
			statement.setInt(8, productDetail.getDiscount());

			if (productDetail.getId() != 0) {
				statement.setLong(9, productDetail.getId());
			}
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DbManager.connectionPool.releaseConnection(connection);
	}

	@Override
	public void remove(Long id) {
		connection = DbManager.connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY.PRODUCT_DETAIL.DELETE);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbManager.connectionPool.releaseConnection(connection);
	}

	@Override
	public List<ProductDetail> findByProductId(Long productId) {
		List<ProductDetail> productDetailList = new ArrayList<>();
		connection = DbManager.connectionPool.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY.PRODUCT_DETAIL.FIND_BY_PRODUCT_ID);
			statement.setLong(1, productId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("id");
				String sku = rs.getString("sku");
				Color color = colorDAO.findById(rs.getLong("color_id"));
				Material material = materialDAO.findById(rs.getLong("material_id"));
				String imageUrl = rs.getString("image_url");
				long unitPrice = rs.getLong("unit_price");
				int unitInStock = rs.getInt("unit_in_stock");
				int discount = rs.getInt("discount");
				Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("date_created"));
				Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("last_updated"));
				boolean active = rs.getBoolean("active");

				ProductDetail productDetail = new ProductDetail(id, sku, null, color, material, imageUrl, unitPrice, unitInStock, discount, dateCreated, lastUpdated, active);
				productDetailList.add(productDetail);
			}
		} catch (Exception e) {
			DbManager.connectionPool.releaseConnection(connection);
			return productDetailList;
		}
		DbManager.connectionPool.releaseConnection(connection);
		return productDetailList;
	}

	@Override
	public ProductDetail findById(Long productId, Long colorId, Long materialId) {
		Product product = productDAO.findById(productId);

		return product.getProducts().stream().
				filter(p -> p.getColor().getId().equals(colorId) && p.getMaterial().getId().equals(materialId))
				.findFirst().orElse(null);
	}

	@Override
	public ProductDetail findBySku(String sku) {
		connection = DbManager.connectionPool.getConnection();
		ProductDetail productDetail = null;
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY.PRODUCT_DETAIL.FIND_BY_SKU);
			statement.setString(1, sku);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Long id = rs.getLong("id");
				Product product = productDAO.findById(rs.getLong("product_id"));
				String imageUrl = rs.getString("image_url");
				Color color = colorDAO.findById(rs.getLong("color_id"));
				Material material = materialDAO.findById(rs.getLong("material_id"));
				long unitPrice = rs.getLong("unit_price");
				int unitInStock = rs.getInt("unit_in_stock");
				int discount = rs.getInt("discount");
				Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("date_created"));
				Date lastUpdated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("last_updated"));
				boolean active = rs.getBoolean("active");
				productDetail = new ProductDetail(id, sku, product, color, material, imageUrl, unitPrice, unitInStock, discount, dateCreated, lastUpdated, active);
			}
		} catch (Exception e) {
			DbManager.connectionPool.releaseConnection(connection);
			return null;
		}
		DbManager.connectionPool.releaseConnection(connection);
		return productDetail;
	}
}
