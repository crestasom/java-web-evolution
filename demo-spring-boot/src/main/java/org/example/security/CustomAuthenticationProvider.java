//package org.example.security;
//
//import org.example.model.User;
//import org.example.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserRepository userRepository;
//
//    public CustomAuthenticationProvider(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new BadCredentialsException("User not found: " + username);
//        }
//
//        // CUSTOM LOGIC: Check if user is active
//        if (!user.isActive()) {
//            throw new BadCredentialsException("User account is disabled");
//        }
//
//        // CUSTOM LOGIC: Compare passwords (using plain text for this demo)
//        if (user.getPassword().equals(password)) {
//            return new UsernamePasswordAuthenticationToken(
//                    username,
//                    password,
//                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
//        } else {
//            throw new BadCredentialsException("Invalid password");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
