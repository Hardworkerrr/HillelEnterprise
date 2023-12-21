package com.hillel.javaee.controller;

import com.hillel.javaee.model.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Controller
@RequestMapping("/checkout")
public class CheckoutServlet {

    @GetMapping()
    public String checkout(@ModelAttribute("productsToOrder") HashMap<Product,String> productToOrder,
                           Model model){
        return "checkout";
    }

    @PostMapping("/success")
    public void successCheckout(HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.append("<p>Order has been successfully created</p>");
        printWriter.append("<a href=/SpringHomework_war>Return to main page</a>");
        printWriter.close();
    }
}
