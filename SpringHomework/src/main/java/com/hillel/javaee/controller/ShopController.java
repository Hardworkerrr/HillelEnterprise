package com.hillel.javaee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.javaee.model.Product;
import com.hillel.javaee.service.impl.ProductManipulationService;
import com.hillel.javaee.util.SpringScriptUtility;
import org.apache.tomcat.util.log.UserDataHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {

    private final Map<Product, String> BASKET_PRODUCTS = new HashMap<>();

    @RequestMapping(value = "/products")
    public String getProducts(@Autowired ProductManipulationService productManipulationService, Model model) {
        List<Product> products = productManipulationService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
    @RequestMapping(value = "/products/{id}")
    public String getProduct(@PathVariable("id") String id,
                             Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String productResourceUrl = SpringScriptUtility
                .readResourceProperties("restapi.properties")
                .get("url");
        Product product = restTemplate.getForObject(productResourceUrl + id, Product.class);
        model.addAttribute("product",product);
        return "product";
    }

    @PostMapping(value = "/addToBasket")
    public String addProductToBasket(@RequestParam(name = "quantity") String quantity,
                                     @RequestParam(name = "productId") String id,
                                     @Autowired ProductManipulationService productManipulationService) {
        BASKET_PRODUCTS.put(productManipulationService.getProductById(Integer.parseInt(id)), quantity);
        return "redirect:/products";
    }

    @PostMapping(value = "/moveToBasket")
    public RedirectView moveToBasket(RedirectAttributes attributes) {
        attributes.addFlashAttribute("basketProducts", BASKET_PRODUCTS);
        return new RedirectView("basket");
    }

    @PostMapping(value = "/returnFromBasket")
    public String returnFromBasket(){
        BASKET_PRODUCTS.clear();
        return "redirect:/products";
    }

}
