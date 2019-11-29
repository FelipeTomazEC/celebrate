package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.PersonDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Person;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoJDBC implements PersonDao {
    private Connection connection;

    public PersonDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Person person) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Pessoa " +
                            "(cpf, nome, email) " +
                            "VALUES (?, ?, ?)"
            );

            preparedStatement.setString(1, person.getCpf());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getEmail());

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
    public void update(Person person) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Person findById(String id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        return null;
    }
}
