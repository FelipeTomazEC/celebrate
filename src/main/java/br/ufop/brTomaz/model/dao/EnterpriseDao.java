package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.entities.Enterprise;

import java.util.List;

public interface EnterpriseDao {
    void insert (Enterprise enterprise);
    Enterprise findByCnpj (String cnpj);
    void deleteByCnpj(String cnpj);
    List<Enterprise> findAll();
}
