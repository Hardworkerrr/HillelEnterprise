package com.hillel.javaee.controller;

import com.hillel.javaee.model.Customer;
import com.hillel.javaee.service.impl.CustomerManipulationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class UserAuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "/login";
    }


    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("/processLogin")
    public String processLogin(CustomerManipulationService customerManipulationService,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password) throws Exception {

        Map<String, String> credentials = customerManipulationService.getAllCredentials();
        for (Map.Entry<String, String> entry : credentials.entrySet()) {
            if (entry.getKey().equals(username) && entry.getValue().equals(password)) {
                return "/product";
            }
        }
        throw new Exception();
    }

    @PostMapping("/processRegister")
    public String processRegister(@RequestParam("nameReg") String name,
                                  @RequestParam("emailReg") String email,
                                  @RequestParam("phoneReg") String phoneNumber,
                                  @RequestParam("usernameReg") String username,
                                  @RequestParam("passwordReg") String password,
                                  CustomerManipulationService customerManipulationService){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customerManipulationService.createCustomerWithCredentials(customer,username,password);
        return "/product";
    }

}
