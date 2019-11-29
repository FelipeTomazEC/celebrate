package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.MarriageDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Marriage;

import java.sql.*;
import java.util.List;

public class MarriageDaoJDBC implements MarriageDao {
    private Connection connection;

    public MarriageDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Marriage marriage) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Cerimonia " +
                            "(horario, endereco) " +
                            "VALUES (?, ?)"
            );

            preparedStatement.setDate(1, new java.sql.Date(marriage.getDate().getTime()));
            preparedStatement.setString(2, marriage.getPlace());

            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT MAX(id) FROM Cerimonia"
            );

            int id = (resultSet.next()) ? resultSet.getInt(1) : -1;
            marriage.setId(id);

            System.out.println("Marriage id:" + marriage.getId());
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Marriage findById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM Cerimonia " +
                            "WHERE  id = ?"
            );

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                java.util.Date date = resultSet.getDate("horario");
                String place = resultSet.getString("endereco");

                return new Marriage(place, date);
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
    public List<Marriage> findAll() {
        return null;
    }
}
