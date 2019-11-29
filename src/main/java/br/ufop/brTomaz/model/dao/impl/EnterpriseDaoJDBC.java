package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.EnterpriseDao;
import br.ufop.brTomaz.model.entities.Enterprise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Enterprise findByCnpj(String cnpj) {
        String sql = "SELECT E.* FROM empresa as E where cnpj = ?";
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            statement.setString(1, cnpj);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String phone = resultSet.getString("telefone");
                String address = resultSet.getString("endereco");
                String name = resultSet.getString("nome");
                return new Enterprise(cnpj, phone, address, name);
            }
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteByCnpj(String cnpj) {

    }

    @Override
    public List<Enterprise> findAll() {
        return null;
    }
}
