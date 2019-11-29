package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.EnterpriseDao;
import br.ufop.brTomaz.model.entities.Enterprise;

import java.sql.*;
import java.util.List;

public class EnterpriseDaoJDBC implements EnterpriseDao {

    private Connection connection;

    public EnterpriseDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Enterprise enterprise) {
        String sql = "INSERT INTO empresa (cnpj, telefone, endereco, nome)" +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, enterprise.getCnpj());
            preparedStatement.setString(2, enterprise.getPhone());
            preparedStatement.setString(3, enterprise.getPlace());
            preparedStatement.setString(4, enterprise.getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Enterprise findById(Integer id) {
        String sql = "SELECT E.* FROM empresa as E where id = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String phone = resultSet.getString("telefone");
                String address = resultSet.getString("endereco");
                String name = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");

                Enterprise enterprise = new Enterprise(cnpj, phone, address, name);
                enterprise.setId(id);

                return enterprise;
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<Enterprise> findAll() {
        return null;
    }

    private Integer getGeneratedId() throws SQLException {
        String sql = "SELECT MAX(id) FROM empresa";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return (resultSet.next()) ? resultSet.getInt(1) : -1;
    }
}
