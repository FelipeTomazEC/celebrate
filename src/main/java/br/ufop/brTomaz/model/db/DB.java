package br.ufop.brTomaz.model.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;

    public static Connection getConnection() {
        if(connection == null) {
            Properties properties = loadProperties();
            String url = properties.getProperty("dburl");
            try {
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return connection;
    }

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (FileNotFoundException e) {
            throw new DbException(e.getMessage());
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    public static void closeConnection() {
        if(connection == null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}
