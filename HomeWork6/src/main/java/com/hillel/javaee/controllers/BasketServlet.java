package com.hillel.javaee.controllers;

import com.hillel.javaee.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("basket.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Product, String> basketProducts = (Map<Product, String>) getServletContext().getAttribute("basketProducts");
        if(req.getParameter("remove") !=null){
            removeProductFromBasket(basketProducts,req);
        }
        resp.sendRedirect("basket");
    }

    private void removeProductFromBasket(Map<Product, String> basketProducts, HttpServletRequest req){
        Set<Map.Entry<Product, String>> entrySet = basketProducts
                .entrySet()
                .stream()
                .filter(k->k.getKey().getId()!=Integer.parseInt(req.getParameter("remove")))
                .collect(Collectors.toSet());
        Map<Product,String> newBasketProducts = new HashMap<>();
        for (Map.Entry<Product,String> entry:
                entrySet) {
            newBasketProducts.put(entry.getKey(),entry.getValue());
        }
        getServletContext().setAttribute("basketProducts", newBasketProducts);
    }
}
