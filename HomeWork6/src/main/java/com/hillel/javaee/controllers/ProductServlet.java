package com.hillel.javaee.controllers;

import com.hillel.javaee.model.Product;
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
        ProductManipulation productManipulation = (ProductManipulation) req.getServletContext().getAttribute("productManipulationService");
        ArrayList<Product> products = productManipulation.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

}
