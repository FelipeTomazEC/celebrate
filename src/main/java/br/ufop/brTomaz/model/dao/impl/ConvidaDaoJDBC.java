package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Person;
import br.ufop.brTomaz.model.entities.Wedding;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConvidaDaoJDBC {
    private Connection connection;

    public ConvidaDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    public void insert(Person person, Wedding wedding) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Convida " +
                            "(fk_pessoa_cpf, fk_casamento_id) " +
                            "VALUES (?, ?)"
            );

            preparedStatement.setString(1, person.getCpf());
            preparedStatement.setInt(2, wedding.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }
}
