package com.hillel.javaee.service.impl;

import com.hillel.javaee.dbmanager.DBConnectionPool;
import com.hillel.javaee.model.Address;
import com.hillel.javaee.model.Customer;
import com.hillel.javaee.repository.AddressDAO;
import com.hillel.javaee.repository.CustomerDAO;
import com.hillel.javaee.service.CustomerManipulation;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component("customerManipulationService")
public class CustomerManipulationService implements CustomerManipulation {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final AddressDAO addressDAO = new AddressDAO();


    public CustomerManipulationService() {

    }


    public String getCredentialsOfUser(int id) {
        return null;
    }

    public Map<String, String> getAllCredentials() {
        Map<String, String> credentialsMap = new HashMap<>();
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT username, password FROM credentials");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                credentialsMap.put(resultSet.getString(1), resultSet.getString(2));
            }
            connection.close();
        } catch (ClassNotFoundException | URISyntaxException | SQLException e) {
            throw new RuntimeException(e);
        }
        return credentialsMap;
    }


    public Address getUserAddressByID(int id) {
        return addressDAO.getAddressOfUser(id);
    }


    @Override
    public ArrayList<Customer> getAllUsers() {
        return customerDAO.getAll();
    }

    @Override
    public void createCustomerWithCredentials(Customer customer, String username, String password) {
        customerDAO.createWithCredentials(customer,username,password);
    }

}
