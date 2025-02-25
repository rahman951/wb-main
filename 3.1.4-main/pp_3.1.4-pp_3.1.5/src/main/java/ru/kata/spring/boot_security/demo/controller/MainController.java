package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard"; // Отправка запроса на отображение страницы dashboard.html
    }

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index"; // Перенаправление на /index
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index"; // Возвращает шаблон index.html
    }
}
