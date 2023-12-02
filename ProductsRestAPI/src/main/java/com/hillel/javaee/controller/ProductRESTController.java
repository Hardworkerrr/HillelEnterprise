package com.hillel.javaee.controller;


import com.hillel.javaee.model.Product;
import com.hillel.javaee.service.ProductServiceInterface;
import com.hillel.javaee.service.impl.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/products")
public class ProductRESTController {

    private final ProductServiceInterface productServiceInterface;

    public ProductRESTController() {
        productServiceInterface= new ProductService();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productServiceInterface.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productServiceInterface.getProductById(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        return productServiceInterface.addProduct(product);
    }

    @PutMapping("/products")
    public Product putProduct(@RequestBody Product newProduct) {
        return productServiceInterface.updateProduct(newProduct);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        return productServiceInterface.removeProduct(id);
    }
}

