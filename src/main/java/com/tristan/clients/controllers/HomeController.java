package com.tristan.clients.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Value("${spring.application.name}")
    private String name = "Tristan";

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
