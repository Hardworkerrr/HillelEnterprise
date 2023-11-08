package com.hillel.javaee.service.impl;

import com.hillel.javaee.models.Address;
import com.hillel.javaee.models.Customer;
import com.hillel.javaee.models.Product;
import com.hillel.javaee.repository.CategoryDAO;
import com.hillel.javaee.repository.ProductDAO;
import com.hillel.javaee.service.ProductManipulation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductManipulationService implements ProductManipulation {
    private final ProductDAO productDAO = new ProductDAO();

    public ProductManipulationService() {

    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return productDAO.getAll();
    }
}
