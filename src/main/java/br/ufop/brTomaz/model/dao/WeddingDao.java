package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.entities.Wedding;

import java.util.List;

public interface WeddingDao {
    void insert(Wedding wedding);
    void update(Wedding wedding);
    void deleteById(Integer id);
    void findById(Integer id);
    List<Wedding> findAll();
}
