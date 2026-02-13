package org.example.security;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User("Admin", "admin@example.com", "admin", "admin123", "ROLE_ADMIN"));
                userRepository.save(new User("User", "user@example.com", "user", "user123", "ROLE_USER"));

                // Add a blocked user for custom authentication demonstration
                User blockedUser = new User("Blocked", "blocked@example.com", "blocked", "password", "ROLE_USER");
                blockedUser.setActive(false);
                userRepository.save(blockedUser);
            }
        };
    }
}
