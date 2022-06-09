package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.District;

import java.util.List;

public interface DistrictDAO extends BaseDAO<District> {
    List<District> findByProvinceId(long provinceId);
}
