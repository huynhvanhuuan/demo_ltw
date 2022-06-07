package vn.edu.hcmuaf.fit.dao;

import java.util.List;

public interface BaseDAO<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T object);

    void removeById(Long id);
}
