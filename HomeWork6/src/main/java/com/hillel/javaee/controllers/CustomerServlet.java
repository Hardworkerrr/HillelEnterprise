package com.hillel.javaee.controllers;


import com.hillel.javaee.model.Customer;
import com.hillel.javaee.service.CustomerManipulation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerManipulation customerManipulation = (CustomerManipulation) req.getServletContext().getAttribute("customerManipulationService");
        ArrayList<Customer> customers = customerManipulation.getAllUsers();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("customer.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
