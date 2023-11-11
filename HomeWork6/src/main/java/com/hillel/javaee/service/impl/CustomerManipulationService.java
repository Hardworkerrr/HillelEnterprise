package com.hillel.javaee.service.impl;

import com.hillel.javaee.models.Address;
import com.hillel.javaee.models.Customer;
import com.hillel.javaee.repository.AddressDAO;
import com.hillel.javaee.repository.CustomerDAO;
import com.hillel.javaee.service.CustomerManipulation;

import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerManipulationService implements CustomerManipulation {

    private final CustomerDAO customerDAO = new CustomerDAO();
    private final AddressDAO addressDAO = new AddressDAO();


    public CustomerManipulationService() {

    }


    public String getCredentials(int id) throws SQLException {
        return null;
    }


    public Address getUserAddressByID(int id) throws SQLException {
        return addressDAO.getAddressOfUser(id);
    }


    @Override
    public ArrayList<Customer> getAllUsers() {
        return customerDAO.getAll();
    }

}
