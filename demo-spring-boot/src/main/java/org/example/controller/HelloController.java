package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/hello")
    public String hello(@RequestParam("name") String name, Model model) {
        model.addAttribute("userName", name);
        return "result";
    }
}
