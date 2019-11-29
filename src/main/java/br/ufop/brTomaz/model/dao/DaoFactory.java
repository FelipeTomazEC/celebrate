package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.dao.impl.*;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.*;

public class DaoFactory {
    public static DAO<Spouse> createSpouse() {
        return new SpouseDaoJDBC(DB.getConnection());
    }
    public static DAO<Person> createPerson() {
        return new PersonDaoJDBC(DB.getConnection());
    }
    public static DAO<Civil> createCivil() {
        return new CivilDaoJDBC(DB.getConnection());
    }
    public static DAO<Marriage> createMarriage() {
        return new MarriageDaoJDBC(DB.getConnection());
    }
    public static DAO<Wedding> createWedding() {
        return new WeddingDaoJDBC(DB.getConnection());
    }
    public static WitnessDao createWitness() { return new WitnessDaoJDBC(DB.getConnection()); }
}
