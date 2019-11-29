package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.WeddingDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Wedding;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                            "(fk_cerimônia_id, fk_civil_id) " +
                            "VALUES (?, ?)"
            );

            preparedStatement.setInt(1, wedding.getCivil().getId());
            preparedStatement.setInt(2, wedding.getMarriage().getId());

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
    public void update(Wedding wedding) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Wedding findById(String id) {
        return null;
    }

    @Override
    public List<Wedding> findAll() {
        return null;
    }
}
