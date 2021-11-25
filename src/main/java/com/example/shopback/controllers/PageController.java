package com.example.shopback.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @GetMapping("/products")
    public String showProducts() {
        return "products";
    }

    @GetMapping("/userPage")
    public String showUserPage() {
        return "user_page";
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
