package com.hillel.javaee.context;

import com.hillel.javaee.service.impl.CustomerManipulationService;
import com.hillel.javaee.service.impl.ProductManipulationService;


public class AppContext {
    private final CustomerManipulationService customerManipulationService;
    private final ProductManipulationService productManipulationService;

    AppContext() {
        this.customerManipulationService = new CustomerManipulationService();
        this.productManipulationService = new ProductManipulationService();
    }

    public CustomerManipulationService getCustomerManipulationService() {
        return customerManipulationService;
    }

    public ProductManipulationService getProductManipulationService() {
        return productManipulationService;
    }
}
