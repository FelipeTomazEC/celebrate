package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.CivilDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Civil;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class CivilDaoJDBC implements CivilDao {
    private Connection connection;

    public CivilDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Civil civil) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Civil " +
                            "(endereco, horario) " +
                            "VALUES (?, ?)"
            );

            preparedStatement.setString(1, civil.getPlace());
            preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));

            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT MAX(id) FROM Civil"
            );

            int id = (resultSet.next()) ? resultSet.getInt(1) : -1;
            civil.setId(id);

            System.out.println("Civil Id: "+ civil.getId());
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Civil civil) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Civil findById(String id) {
        return null;
    }

    @Override
    public List<Civil> findAll() {
        return null;
    }
}
