package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.SpouseDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Spouse;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.List;

public class SpouseDaoJDBC implements SpouseDao {
    private Connection connection;

    public SpouseDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Spouse spouse) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Conjuge " +
                            "(senha, telefone, fk_Pessoa_cpf) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setString(1, spouse.getPassword());
            preparedStatement.setString(2, spouse.getPhone());
            preparedStatement.setString(3, spouse.getCpf());

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
    public void update(Spouse spouse) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Spouse findById(String id) {
        return null;
    }

    @Override
    public List<Spouse> findAll() {
        return null;
    }
}
