package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.CivilDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Civil;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            preparedStatement.setDate(2, new java.sql.Date(civil.getDate().getTime()));

            int rowsAffected = preparedStatement.executeUpdate();

            Alert alert;

            if(rowsAffected > 0) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cônjuge");
                alert.setHeaderText("Confirmação de cadastro");
                alert.setContentText("Cadastro efetuado com sucesso");
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cônjuge");
                alert.setHeaderText("Confirmação de cadastro");
                alert.setContentText("Não foi possível realizar o cadastro");
                throw new DbException("Unexpected error! No rows affected");
            }
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
