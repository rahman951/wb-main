package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewProductController {

    @GetMapping("/product")
    public String renderCurrentProduct() {
        return "product";
    }
}
