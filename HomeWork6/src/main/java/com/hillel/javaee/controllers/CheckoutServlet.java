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
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = {"/checkout","/checkout/success"})
public class CheckoutServlet extends HttpServlet {

    Map<Product,String> productsToOrder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        productsToOrder = new HashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderedProducts",productsToOrder);
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if(req.getParameter("selectedProduct")!=null){
            getAllProductsToOrder(req);
        }
        if(req.getHttpServletMapping().getMatchValue().equals("checkout/success")){
            PrintWriter printWriter = resp.getWriter();
            printWriter.append("<p>Order has been successfully created</p>");
            printWriter.append("<a href=/L6/welcome>Return to main page</a>");
            printWriter.close();
        }
        resp.sendRedirect("checkout");
    }

    private void getAllProductsToOrder(HttpServletRequest req){
        productsToOrder = new HashMap<>();
        ProductManipulation productManipulation = (ProductManipulation) req.getServletContext().getAttribute("productManipulationService");
        ArrayList<String> quantityParametersList = new ArrayList<>();
        req.getParameterNames().asIterator().forEachRemaining(s -> {
            if (s.matches("productQuantity_\\d")) {
                quantityParametersList.add(s);
            }
        });
        String[] quantityParametersArray = quantityParametersList.toArray(new String[0]);
        String[] selectedItemsIds = req.getParameterValues("selectedProduct");
        for (String itemId: selectedItemsIds) {
            for (String quantity: quantityParametersArray) {
                if(Integer.parseInt(itemId)==Integer.parseInt(quantity.split("_")[1])){
                    productsToOrder.put(productManipulation.getProductById(Integer.parseInt(itemId)),req.getParameter(quantity));
                }
            }
        }

    }
}
