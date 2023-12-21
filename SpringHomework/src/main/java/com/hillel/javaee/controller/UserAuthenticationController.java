package com.hillel.javaee.controller;

import com.hillel.javaee.validation.entity.AuthenticationEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserAuthenticationController {

    @GetMapping("/login")
    public String login(@ModelAttribute("authenticationEntity") AuthenticationEntity authenticationEntity) {
        return "login";
    }


    @GetMapping("/register")
    public String register(@ModelAttribute("authenticationEntity") AuthenticationEntity authenticationEntity) {
        return "register";
    }

    @PostMapping("/processLogin")
    public String processLogin(@Valid @ModelAttribute("authenticationEntity") AuthenticationEntity authenticationEntity,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login";
        }
        return "redirect:products";
    }

    @PostMapping("/processRegister")
    public String processRegister(@Valid @ModelAttribute("authenticationEntity") AuthenticationEntity authenticationEntity,
                                  BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        return "redirect:products";
    }

}
