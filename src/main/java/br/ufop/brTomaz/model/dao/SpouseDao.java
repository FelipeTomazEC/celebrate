package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.entities.Spouse;

import java.util.List;

public interface SpouseDao {
    void insert (Spouse spouse);
    Spouse findById (Integer id);
    List<Spouse> findAll ();
    void update (Spouse spouse);
    void deleteById(Integer id);
}
