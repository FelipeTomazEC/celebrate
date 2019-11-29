package br.ufop.brTomaz.model.dao;

import java.util.List;

public interface DAO<T> {
    void insert(T t);
    void update(T t);
    void deleteById(String id);
    T findById(String id);
    List<T> findAll();
}
