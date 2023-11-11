package com.hillel.javaee.repository;

import com.hillel.javaee.dbmanager.DBConnectionPool;
import com.hillel.javaee.model.Customer;
import com.hillel.javaee.utils.SpringScriptUtility;

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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SpringScriptUtility.readResourceSql("sql/getAllCustomers.sql"));
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
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SpringScriptUtility.
                    readResourceSql("sql/createCustomer.sql"));
            preparedStatement.setInt(1, getCustomersCount() + 1);
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setDate(5, customer.getBirthday());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException | URISyntaxException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createWithCredentials(Customer customer, String username, String password) {
        create(customer);
        Customer newCustomer = getCustomerByEmail(customer.getEmail());
        System.out.println(newCustomer.getId());
        System.out.println(newCustomer.getName());
        System.out.println(newCustomer.getEmail());
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SpringScriptUtility.
                    readResourceSql("sql/createCustomerWithCredentials.sql"));
            preparedStatement.setInt(1, getCredentialsCount()+1);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setInt(4, newCustomer.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Customer customer, String[] params) {

    }

    @Override
    public void delete(Customer customer) {

    }

    public Customer getCustomerByEmail(String email) {
        Customer customer = new Customer();
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SpringScriptUtility.
                    readResourceSql("sql/getCustomerByEmail.sql"));
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setPhoneNumber(resultSet.getString(3));
                customer.setEmail(email);
                customer.setBirthday(resultSet.getDate(4));
            }
        } catch (ClassNotFoundException | SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    public int getCustomersCount() {
        int count=0;
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SpringScriptUtility.
                    readResourceSql("sql/getCustomersCount.sql"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public int getCredentialsCount() {
        int count=0;
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SpringScriptUtility.
                    readResourceSql("sql/getCredentialsCount.sql"));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
