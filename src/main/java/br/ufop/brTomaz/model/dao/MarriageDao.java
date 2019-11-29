package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.entities.Marriage;

import java.util.List;

public interface MarriageDao  {
    void insert(Marriage marriage);
    Marriage findById(Integer id);
    public List<Marriage> findAll();
}
