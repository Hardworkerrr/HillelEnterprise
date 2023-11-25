package com.hillel.javaee.controller;

import com.hillel.javaee.model.Product;
import com.hillel.javaee.service.impl.ProductManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ShopController {

    private final Map<Product, String> basketProducts = new HashMap<>();

    @RequestMapping(value = "/products")
    public String getProducts(@Autowired ProductManipulationService productManipulationService, Model model) {
        ArrayList<Product> products = productManipulationService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping(value = "/addToBasket")
    public String addProductToBasket(@RequestParam(name = "quantity") String quantity,
                                     @RequestParam(name = "productId") String id,
                                     @Autowired ProductManipulationService productManipulationService) {
        basketProducts.put(productManipulationService.getProductById(Integer.parseInt(id)), quantity);
        return "redirect:/products";
    }

    @PostMapping(value = "/moveToBasket")
    public RedirectView moveToBasket(RedirectAttributes attributes) {
        attributes.addFlashAttribute("basketProducts", basketProducts);
        return new RedirectView("basket");
    }

    @PostMapping(value = "/returnFromBasket")
    public String returnFromBasket(){
        basketProducts.clear();
        return "redirect:/products";
    }

}
