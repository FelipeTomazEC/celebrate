package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.MarriageDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Marriage;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                    "INSERT INTO Cerimônia " +
                            "(horario, endereco) " +
                            "VALUES (?, ?)"
            );

            preparedStatement.setDate(1, new java.sql.Date(marriage.getDate().getTime()));
            preparedStatement.setString(2, marriage.getPlace());

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
    public void update(Marriage marriage) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Marriage findById(String id) {
        return null;
    }

    @Override
    public List<Marriage> findAll() {
        return null;
    }
}
