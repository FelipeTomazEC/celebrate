package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.PersonDao;
import br.ufop.brTomaz.model.dao.SpouseDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Spouse;
import br.ufop.brTomaz.security.SegurancaSistema;

import java.sql.*;
import java.util.List;

public class SpouseDaoJDBC implements SpouseDao {
    private Connection connection;

    public SpouseDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public void insert(Spouse spouse) {
        PreparedStatement preparedStatement = null;
        PersonDao personDao = new PersonDaoJDBC(DB.getConnection());
        personDao.insert(spouse);

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Conjuge " +
                            "(senha, telefone, fk_Pessoa_id) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setString(1, SegurancaSistema.
                    criptografarSenha(spouse.getPassword()));
            preparedStatement.setString(2, spouse.getPhone());
            preparedStatement.setInt(3, spouse.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Spouse spouse) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Spouse findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM Conjuge, Pessoa " +
                            "WHERE Conjuge.fk_Pessoa_id = ?"
            );
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String password = resultSet.getString("senha");
                String phone = resultSet.getString("telefone");

                Spouse spouse = new Spouse();
                spouse.setPhone(phone);
                spouse.setPassword(password);
                return spouse;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
        return null;
    }

    @Override
    public List<Spouse> findAll() {
        return null;
    }

}
