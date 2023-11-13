package com.hillel.javaee.context;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppContextInitializeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("customerManipulationService", new AppContext().getCustomerManipulationService());
        sce.getServletContext().setAttribute("productManipulationService", new AppContext().getProductManipulationService());
    }

}
