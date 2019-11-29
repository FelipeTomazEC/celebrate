package br.ufop.brTomaz.model.dao.impl;

import br.ufop.brTomaz.model.dao.EnterpriseDao;
import br.ufop.brTomaz.model.dao.ServiceDao;
import br.ufop.brTomaz.model.db.DB;
import br.ufop.brTomaz.model.entities.Enterprise;
import br.ufop.brTomaz.model.entities.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoJDBC implements ServiceDao {

    private Connection connection;

    public ServiceDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Service service) {
        String sql = "INSERT INTO Servico (nome, valor, fk_Empresa_id)" +
                "VALUES (?, ?, ?)";
        PreparedStatement preparedStatement;

        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, service.getName());
            preparedStatement.setDouble(2, service.getValue());
            preparedStatement.setString(3, service.getEnterprise().getCnpj());

            preparedStatement.executeUpdate();

            Integer id = this.getGeneratedId();
            service.setId(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Service findById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(Service service) {

    }

    @Override
    public List<Service> findAll() {
        String sql = "SELECT S.* FROM servico AS S";
        List<Service> serviceList = new ArrayList<>();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String name = resultSet.getString("nome");
                Double value = resultSet.getDouble("valor");
                Integer id = resultSet.getInt("id");
                Integer enterpriseId = resultSet.getInt("fk_Empresa_id");

                Enterprise enterprise = this.getEnterprise(enterpriseId);
                Service service = new Service(name, value, enterprise);
                service.setId(id);

                serviceList.add(service);
            }

            return serviceList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer getGeneratedId() throws SQLException {
        String sql = "SELECT MAX(id) FROM Servico";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        return (resultSet.next()) ? resultSet.getInt(1) : -1;
    }

    private Enterprise getEnterprise (Integer id) throws SQLException {
        EnterpriseDao enterpriseDao = new EnterpriseDaoJDBC(DB.getConnection());
        return enterpriseDao.findById(id);
    }
}
