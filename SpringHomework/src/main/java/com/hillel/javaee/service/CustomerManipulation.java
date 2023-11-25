package com.hillel.javaee.service;


import com.hillel.javaee.model.Address;
import com.hillel.javaee.model.Customer;

import java.util.ArrayList;
import java.util.Map;

public interface CustomerManipulation {

    String getCredentialsOfUser(int id);
    Map<String,String> getAllCredentials();

    Address getUserAddressByID(int id);

    ArrayList<Customer> getAllUsers();

    void createCustomerWithCredentials(Customer customer, String username, String password);
}
