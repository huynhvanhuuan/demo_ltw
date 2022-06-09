package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.Ward;

import java.util.List;

public interface WardDAO extends BaseDAO<Ward> {
    List<Ward> findByDistrictId(Long districtId);
}
