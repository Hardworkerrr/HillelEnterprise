package com.hillel.javaee.controllers;

import com.hillel.javaee.model.Product;
import com.hillel.javaee.service.ProductManipulation;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    HashMap<Product,String> basketProducts;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        basketProducts = new HashMap<>();
        getServletContext().setAttribute("basketProducts",basketProducts);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductManipulation productManipulation = (ProductManipulation) req.getServletContext().getAttribute("productManipulationService");
        ArrayList<Product> products = productManipulation.getAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("productId")!=null){
            addProductToBasket(req);
        }
        resp.sendRedirect("product");
    }

    private void addProductToBasket(HttpServletRequest req){
        ProductManipulation productManipulation = (ProductManipulation) req.getServletContext().getAttribute("productManipulationService");
        Product newBasketProduct = productManipulation.getProductById(Integer.parseInt(req.getParameter("productId")));
        basketProducts = (HashMap<Product, String>) getServletContext().getAttribute("basketProducts");
        basketProducts.put(newBasketProduct, req.getParameter("quantity"));
        getServletContext().setAttribute("basketProducts",basketProducts);
    }
}
