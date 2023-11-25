package com.hillel.javaee.controller;

import com.hillel.javaee.model.Product;
import com.hillel.javaee.service.ProductManipulation;
import com.hillel.javaee.service.impl.ProductManipulationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class BasketController {

    HashMap<Product,String> productsInBasket = new HashMap<>();

    @GetMapping("/basket")
    public String basket(@ModelAttribute("basketProducts") HashMap<Product,String> basketProducts,
                         Model model){
        productsInBasket.putAll(basketProducts);
        model.addAttribute("productsInBasket",productsInBasket);
        return "/basket";
    }

    @PostMapping("/removeProduct")
    public String removeProductFromBasket(@ModelAttribute("basketProducts") HashMap<Product, String> basketProducts,
                                          @RequestParam("removeId") String id,
                                          ProductManipulationService productManipulationService){
        productsInBasket.remove(productManipulationService.getProductById(Integer.parseInt(id)));
        return "redirect:/basket";
    }

    @PostMapping("/startCheckout")
    public RedirectView startCheckout(RedirectAttributes attributes,
                                      ProductManipulationService productManipulationService,
                                      HttpServletRequest req){
        HashMap<Product,String> productsToOrder = new HashMap<>();
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
                    productsToOrder.put(productManipulationService.getProductById(Integer.parseInt(itemId)),req.getParameter(quantity));
                }
            }
        }
        attributes.addFlashAttribute("productsToOrder",productsToOrder);
        return new RedirectView("checkout");
    }
}
