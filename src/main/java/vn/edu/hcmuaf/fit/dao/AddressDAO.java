package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.Address;

import java.util.List;

public interface AddressDAO extends BaseDAO<Address> {
	List<Address> findByTrademarkId(Long trademarkId);
	List<Address> findByUserId(Long userId);
	Address findByPath(String path);
}
