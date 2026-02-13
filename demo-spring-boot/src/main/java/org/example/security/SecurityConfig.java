package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login", "/css/**", "/js/**").permitAll()
				.anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").permitAll())
				.logout(logout -> {
					logout.logoutUrl("/logout");
					logout.logoutSuccessUrl("/");
				});
		return http.build();
	}

	/*
	 * METHOD 1: Default Starter Security By default, Spring Boot Security provides
	 * a default user with a generated password (visible in the console) if no
	 * UserDetailsService or AuthenticationProvider is defined.
	 */
	// @Bean
	// public UserDetailsService defaultUserDetailsService() {
	// UserDetails user = User.withDefaultPasswordEncoder()
	// .username("user")
	// .password("password")
	// .roles("USER")
	// .build();
	// return new InMemoryUserDetailsManager(user);
	// }

	/*
	 * METHOD 2: Configuring Password Encoder Instead of using {noop} or default
	 * encoders, we can define a PasswordEncoder bean. BCrypt is the recommended
	 * standard for hashing passwords.
	 */
	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

	/*
	 * METHOD 3: Loading User Details from Database Using a custom
	 * UserDetailsService allows us to load user information from our own database
	 * (e.g., via JPA UserRepository).
	 */
	// @Bean
	// public UserDetailsService
	// customUserDetailsService(org.example.repository.UserRepository
	// userRepository) {
	// return new org.example.security.CustomUserDetailsService(userRepository);
	// }

	/*
	 * METHOD 4: Custom Authentication Checking Mechanism By defining a custom
	 * AuthenticationProvider, we gain full control over the authentication process,
	 * including password matching and user status checks. Our implementation
	 * (CustomAuthenticationProvider) checks user.isActive().
	 */
	// @Bean
	// public AuthenticationProvider
	// customAuthenticationProvider(org.example.repository.UserRepository
	// userRepository) {
	// return new org.example.security.CustomAuthenticationProvider(userRepository);
	// }

	// For this demo to work without manual uncommenting, let's provide a simple
	// NoOp encoder if none is active
//        @Bean
//        public PasswordEncoder fallbackPasswordEncoder() {
//                return NoOpPasswordEncoder.getInstance();
//        }
}
