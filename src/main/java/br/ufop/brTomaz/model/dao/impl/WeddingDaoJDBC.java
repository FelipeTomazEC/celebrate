package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.WeddingDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Wedding;

import java.sql.*;
import java.util.List;

public class WeddingDaoJDBC implements WeddingDao {
    private Connection connection;

    public WeddingDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Wedding wedding) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Casamento " +
                            "(fk_cerimonia_id, fk_civil_id, fk_Conjuge1, fk_Conjuge2) " +
                            "VALUES (?, ?, ?, ?)"
            );

            preparedStatement.setInt(2, wedding.getCivil().getId());
            preparedStatement.setInt(1, wedding.getMarriage().getId());
            preparedStatement.setInt(3, wedding.getSpouse1().getId());
            preparedStatement.setInt(4, wedding.getSpouse2().getId());

            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT MAX(id) FROM Casamento"
            );

            int id = (resultSet.next()) ? resultSet.getInt(1) : -1;
            wedding.setId(id);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Wedding wedding) {

    }

    @Override
    public void deleteById(Integer id) {
    }

    @Override
    public void findById(Integer id) {
    }

    @Override
    public List<Wedding> findAll() {
        return null;
    }

    public Integer idMarriage(Integer personId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT fk_Cerimonia_id " +
                            "FROM Casamento " +
                            "WHERE fk_Conjuge1 = ? OR fk_Conjuge2 = ?"
            );

            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, personId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt("fk_Cerimonia_id");
            }
        } catch (SQLException e) {
            throw  new DbException(e.getMessage());
        }
        return null;
    }
}
