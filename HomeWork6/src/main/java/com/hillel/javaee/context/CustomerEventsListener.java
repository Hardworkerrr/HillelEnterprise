package com.hillel.javaee.context;

import com.hillel.javaee.models.Customer;
import com.hillel.javaee.service.CustomerManipulation;
import com.hillel.javaee.service.impl.CustomerManipulationService;
import com.hillel.javaee.service.impl.ProductManipulationService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.HashMap;
import java.util.Map;



@WebListener
public class CustomerEventsListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("customerManipulationService", new AppContext().getCustomerManipulationService());
        sce.getServletContext().setAttribute("productManipulationService", new AppContext().getProductManipulationService());
    }


}
