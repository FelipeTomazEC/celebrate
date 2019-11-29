package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.entities.Enterprise;
import br.ufop.brTomaz.model.entities.Service;

import java.util.List;

public interface ServiceDao {
    void insert (Service service);
    Service findById (Integer id);
    void deleteById(Integer id);
    void update(Service service);
    List<Service> findAll();
}
