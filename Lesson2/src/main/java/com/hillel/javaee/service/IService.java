package com.hillel.javaee.service;

import java.sql.Date;
public interface IService {
    String getAllEmailsByUserId(int id);
    String addNewCustomer(int id, String name, Date birthdate);
}
