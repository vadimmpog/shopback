package com.example.shopback.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @GetMapping("/products")
    public String showProducts() {
        return "products";
    }

    @GetMapping("/page")
    public String showUserPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().toString();
        if(role.equals("[ADMIN]")){
            return "admin_page";
        }else return "user_page";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "registration";
    }

    @GetMapping("/cart")
    public String showCartPage() {
        return "cart";
    }

    @GetMapping("/orders")
    public String showOrders() {
        return "orders";
    }

    @RequestMapping("/users")
    public String showUsers() {
        return "users";
    }

}
