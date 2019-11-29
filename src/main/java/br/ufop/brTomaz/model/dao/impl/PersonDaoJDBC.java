package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.PersonDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.db.DbException;
import br.ufop.brTomaz.model.entities.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJDBC implements PersonDao {
    private Connection connection;

    public PersonDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Person person) {
        System.out.println("Insert person called.");

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO Pessoa " +
                            "(cpf, nome, email, sexo) " +
                            "VALUES (?, ?, ?, ?)"
            );

            preparedStatement.setString(1, person.getCpf());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getSex());
            preparedStatement.executeUpdate();

            Integer id = this.getGeneratedId();
            person.setId(id);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Person person) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE Pessoa " +
                            "SET nome = ?, email = ? " +
                            "WHERE cpf = ?"
            );

            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getCpf());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void deleteById(String id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM Pessoa " +
                            "WHERE cpf = ?"
            );

            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public Person findById(String id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM Pessoa " +
                            "WHERE cpf = ?"
            );

            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String sex = resultSet.getString("sex");

                return new Person(name, cpf, email, sex);
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
    public List<Person> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * " +
                            "FROM Pessoa "  +
                            " ORDER BY Pessoa.nome"
            );

            List<Person> people = new ArrayList<>();

            while (resultSet.next()) {
                people.add(getPerson(resultSet));
            }
            return people;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    public Person retriveUser(String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * " +
                            "FROM Pessoa " +
                            "WHERE email = ?"
            );
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            return getPerson(resultSet);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(preparedStatement);
            DB.closeResultSet(resultSet);
        }
    }

    public Person getPerson(ResultSet resultSet) throws SQLException {
        if(resultSet.next()) {
            String cpf = resultSet.getString("cpf");
            String name = resultSet.getString("nome");
            String email = resultSet.getString("email");
            String sex = resultSet.getString("sexo");
            Integer id = resultSet.getInt("id");

            Person person = new Person(name, cpf, email, sex);
            person.setId(id);

            return person;
        }
        return null;
    }

    private Integer getGeneratedId() throws SQLException {
        String sql = "SELECT MAX(id) FROM pessoa";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return (resultSet.next()) ? resultSet.getInt(1) : -1;
    }
}
