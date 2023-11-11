package com.hillel.javaee.controllers;

import com.hillel.javaee.models.Customer;
import com.hillel.javaee.models.Product;
import com.hillel.javaee.service.CustomerManipulation;
import com.hillel.javaee.service.ProductManipulation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductManipulation customerManipulation = (ProductManipulation) req.getServletContext().getAttribute("productManipulationService");
        ArrayList<Product> products = customerManipulation.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
