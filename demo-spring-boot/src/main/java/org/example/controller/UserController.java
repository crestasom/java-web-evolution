package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/form")
    public String showForm() {
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam String name, @RequestParam String email) {
        userRepository.save(new User(name, email));
        return "redirect:/users/list";
    }

    @GetMapping("/list")
    public String listUsers(@RequestParam(required = false) String search, Model model) {
        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("users",
                    userRepository.findByNameContaining(search, org.springframework.data.domain.PageRequest.of(0, 10)));
        } else {
            model.addAttribute("users", userRepository.findAll());
        }
        return "user-list";
    }
}
