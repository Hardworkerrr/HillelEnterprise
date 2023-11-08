package com.hillel.javaee.service;


import com.hillel.javaee.models.Address;
import com.hillel.javaee.models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerManipulation {

    String getCredentials(int id) throws SQLException;

    Address getUserAddressByID(int id) throws SQLException;

    ArrayList<Customer> getAllUsers();

}
