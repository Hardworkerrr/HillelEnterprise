package com.hillel.javaee.service.impl;

import com.hillel.javaee.service.IService;

import java.sql.*;

public class QueryService implements IService {

    private Connection connection;

    public QueryService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getAllEmailsByUserId(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.id, c.name, e.address FROM customer AS c INNER JOIN email_address AS e on c.id = e.customer_id WHERE c.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            StringBuilder stringBuilder = new StringBuilder();
            if (!resultSet.isBeforeFirst()) {
                return Integer.toString(id);
            }
            while (resultSet.next()) {
                stringBuilder.append(resultSet.getInt(1)).append(" ");
                stringBuilder.append(resultSet.getString(2)).append(" ");
                stringBuilder.append(resultSet.getString(3)).append(" ");
                stringBuilder.append("\n");
            }
            resultSet.close();
            return stringBuilder.toString();
        } catch (SQLException e) {
            return "Bad Request";
        }
    }

    @Override
    public String addNewCustomer(int id, String name, Date birthdate) {
        try {
            connection.setAutoCommit(true);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer (id, name, birthdate) VALUES (?, ?, ?)");
            if (Integer.valueOf(id) == null || name.isEmpty() || birthdate == null) {
                return String.format("%s, %s, %s", id, name, birthdate);
            }
            System.out.println("name - " + name);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, birthdate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return String.format("%s, %s, %s", id, name, birthdate);
        }
        return null;
    }
}
