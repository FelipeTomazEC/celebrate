package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.WitnessDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.model.entities.Wedding;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WitnessDaoJDBC implements WitnessDao {
    private Connection connection;

    public WitnessDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public void insert(Person person, Wedding wedding) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Testemunha " +
                            "(fk_pessoa_cpf, fk_casamento_id) " +
                            "VALUES (?, ?)"
            );

            preparedStatement.setString(1, person.getCpf());
            preparedStatement.setInt(2, wedding.getId());

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
}
