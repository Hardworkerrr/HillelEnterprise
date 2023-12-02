package com.hillel.javaee.service;

import com.hillel.javaee.model.Product;
import java.util.List;

public interface ProductServiceInterface {

    List<Product> getAllProducts();
    Product getProductById(int id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    String removeProduct(int id);
}
