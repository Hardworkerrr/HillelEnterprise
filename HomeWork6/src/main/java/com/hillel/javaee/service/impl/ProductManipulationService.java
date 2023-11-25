package com.hillel.javaee.service.impl;

import com.hillel.javaee.model.Product;
import com.hillel.javaee.repository.ProductDAO;
import com.hillel.javaee.service.ProductManipulation;

import java.util.ArrayList;

public class ProductManipulationService implements ProductManipulation {
    private final ProductDAO productDAO = new ProductDAO();

    public ProductManipulationService() {

    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return productDAO.getAll();
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getById(id);
    }
}
