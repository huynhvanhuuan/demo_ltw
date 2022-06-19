package vn.edu.hcmuaf.fit.dao;

import vn.edu.hcmuaf.fit.entity.AppRole;

import java.util.List;

public interface AppRoleDAO extends BaseDAO<AppRole> {
    List<AppRole> findByUserId(Long userId);
    AppRole findByName(String name);
}
