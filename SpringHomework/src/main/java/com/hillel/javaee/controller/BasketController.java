package com.hillel.javaee.controller;

import com.hillel.javaee.model.Product;
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


import java.util.*;

@Controller
public class BasketController {

    private final Map<Product,String> PRODUCTS_IN_BASKET = new HashMap<>();

    @GetMapping("/basket")
    public String basket(@ModelAttribute("basketProducts") HashMap<Product,String> basketProducts,
                         Model model){
        PRODUCTS_IN_BASKET.putAll(basketProducts);
        model.addAttribute("productsInBasket",PRODUCTS_IN_BASKET);
        return "/basket";
    }

    @PostMapping("/removeProduct")
    public String removeProductFromBasket(@ModelAttribute("basketProducts") HashMap<Product, String> basketProducts,
                                          @RequestParam("removeId") String id,
                                          ProductManipulationService productManipulationService){
        PRODUCTS_IN_BASKET.remove(productManipulationService.getProductById(Integer.parseInt(id)));
        return "redirect:/basket";
    }

    @PostMapping("/startCheckout")
    public RedirectView startCheckout(RedirectAttributes attributes,
                                      ProductManipulationService productManipulationService,
                                      HttpServletRequest req){
        Map<Product,String> productsToOrder = new HashMap<>();
        List<String> quantityParametersList = new ArrayList<>();
        req.getParameterNames().asIterator().forEachRemaining(s -> {
            if (s.matches("productQuantity_\\d")) {
                quantityParametersList.add(s);
            }
        });
        List<String> selectedItemsIds = Arrays.asList(req.getParameterValues("selectedProduct"));
        quantityParametersList.forEach(quantityParameter->{
            String quantityId = quantityParameter.split("_")[1];
            if(selectedItemsIds.contains(quantityId)){
                productsToOrder.put(productManipulationService.getProductById(Integer.parseInt(quantityId)),
                        req.getParameter(quantityParameter));
            }
        });
        attributes.addFlashAttribute("productsToOrder",productsToOrder);
        return new RedirectView("checkout");
    }
}
