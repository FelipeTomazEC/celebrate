package br.ufop.brTomaz.model.dao;

import br.ufop.brTomaz.model.dao.impl.*;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.*;

public class DaoFactory {
    public static PersonDao createPerson() {
        return new PersonDaoJDBC(DB.getConnection());
    }
    public static CivilDao createCivil() {
        return new CivilDaoJDBC(DB.getConnection());
    }
    public static MarriageDao createMarriage() {
        return new MarriageDaoJDBC(DB.getConnection());
    }
    public static WeddingDao createWedding() { return new WeddingDaoJDBC(DB.getConnection()); }
    public static WitnessDao createWitness() { return new WitnessDaoJDBC(DB.getConnection()); }
    public static SpouseDao createSpouse() { return new SpouseDaoJDBC(DB.getConnection()); }
}
