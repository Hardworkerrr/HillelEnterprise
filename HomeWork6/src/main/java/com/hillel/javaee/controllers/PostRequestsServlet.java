package com.hillel.javaee.controllers;

import com.hillel.javaee.model.Customer;
import com.hillel.javaee.service.CustomerManipulation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/postProcess")
public class PostRequestsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerManipulation customerManipulation = (CustomerManipulation) req.getServletContext().getAttribute("customerManipulationService");
        if(req.getParameter("nameReg")!=null){
            Customer customer = new Customer();
            customer.setName(req.getParameter("nameReg"));
            customer.setEmail(req.getParameter("emailReg"));
            customer.setPhoneNumber(req.getParameter("phoneReg"));
            customerManipulation.createCustomerWithCredentials(customer,req.getParameter("usernameReg"),req.getParameter("passwordReg"));
            resp.sendRedirect("customer");
        }
        if (req.getParameter("username")!=null && req.getParameter("password")!=null) {
            Map<String, String> credentials = customerManipulation.getAllCredentials();
            for (Map.Entry<String, String> entry : credentials.entrySet()) {
                if (entry.getKey().equals(req.getParameter("username")) && entry.getValue().equals(req.getParameter("password"))) {
                    resp.sendRedirect("customer");
                }
            }
        }
    }
}
