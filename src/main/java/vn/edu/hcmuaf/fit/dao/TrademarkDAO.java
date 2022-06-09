package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.Trademark;

public interface TrademarkDAO extends BaseDAO<Trademark> {
	void addAddress(Long trademarkId, Long addressId);
	Trademark findByName(String name);
	Trademark findByWebsite(String website);
}
