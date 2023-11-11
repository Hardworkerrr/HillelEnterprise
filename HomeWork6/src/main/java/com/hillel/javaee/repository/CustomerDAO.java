package com.hillel.javaee.repository;

import com.hillel.javaee.dbmanager.DBConnectionPool;
import com.hillel.javaee.models.Customer;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO implements DefaultOperationsDAO<Customer> {
    private ArrayList<Customer> customers = new ArrayList<>();


    public CustomerDAO(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public CustomerDAO() {

    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public ArrayList<Customer> getAll() {
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, phone_number, email,birthday FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setPhoneNumber(resultSet.getString(3));
                customer.setEmail(resultSet.getString(4));
                customer.setBirthday(resultSet.getDate(5));
                customer.setAddress(new AddressDAO().getAddressOfUser(resultSet.getInt(1)));
                customers.add(customer);
            }
            connection.close();

        } catch (SQLException | URISyntaxException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void create(Customer customer) {

    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void delete(Customer customer) {

    }


}
