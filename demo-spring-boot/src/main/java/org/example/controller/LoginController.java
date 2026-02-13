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
     * EVOLUTION NOTE: Manual handling of POST /login.
     * In the Servlet project, we manually handled this in doPost().
     * In Spring Security, this is AUTOMATED by the
     * UsernamePasswordAuthenticationFilter.
     * If we were to handle it manually in Spring, we would inject
     * AuthenticationManager
     * and call authenticate() here.
     */
    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password) {
        // This method is primarily for demonstration/placeholder.
        // Spring Security Filters usually intercept this before it reaches the
        // controller.
        return "redirect:/user/list";
    }
}
