package com.hillel.javaee.repository;

import com.hillel.javaee.dbmanager.DBConnectionPool;
import com.hillel.javaee.model.Address;
import com.hillel.javaee.model.Order;
import com.hillel.javaee.utils.SpringScriptUtility;


import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressDAO implements DefaultOperationsDAO<Address> {
    private ArrayList<Order> addresses = new ArrayList<>();

    public AddressDAO(ArrayList<Order> addresses) {
        this.addresses = addresses;
    }

    public AddressDAO(){

    }

    public ArrayList<Order> getOrders() {
        return addresses;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.addresses = orders;
    }

    public Address getAddressOfUser(int id){
        Address address = new Address();
        try {
            Connection connection = DBConnectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    SpringScriptUtility.readResourceSql("sql/getAddressOfUser.sql"));
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                address.setId(resultSet.getInt(1));
                address.setStreet(resultSet.getString(2));
                address.setCity(resultSet.getString(3));
                address.setPostalCode(resultSet.getString(4));
                address.setCountry(resultSet.getString(5));
                address.setCustomerId(id);
            }
            connection.close();
        } catch (ClassNotFoundException | URISyntaxException | SQLException e) {
            throw new RuntimeException(e);
        }
        return address;
    }

    @Override
    public ArrayList<Address> getAll() {
        return null;
    }

    @Override
    public Address getById(int id) {
        return null;
    }

    @Override
    public void create(Address address) {

    }

    @Override
    public void update(Address address, String[] params) {

    }

    @Override
    public void delete(Address address) {

    }
}
