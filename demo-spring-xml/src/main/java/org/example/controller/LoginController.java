package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * EVOLUTION NOTE: In Spring Security XML config, the POST /login
     * is handled by the framework filters. This method serves as a
     * conceptual equivalent to the LoginServlet's doPost().
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        return "redirect:/user/list";
    }
}
