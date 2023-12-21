package com.hillel.javaee.service;

import com.hillel.javaee.model.Product;

import java.util.ArrayList;

public interface ProductManipulation {
    ArrayList<Product> getAllProducts();
    Product getProductById(int id);
}
