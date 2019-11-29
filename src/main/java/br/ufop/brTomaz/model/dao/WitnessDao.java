package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.model.entities.Wedding;

public interface WitnessDao {
    void insert(Person person, Wedding wedding);
}
