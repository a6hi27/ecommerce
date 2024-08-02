package com.sareepuram.ecommerce.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import com.sareepuram.ecommerce.security.CustomUserDetails;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get all users from User table
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // Add a user to the User table
    public User addUser(User user) {
        // user.setProducts(new ArrayList<Cart>());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Update a user
    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    // Delete a user
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(new User());
    }

    public User findByUserId(Integer userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    // Get the currently logged-in user
    public User getCurrentUser(HttpSession httpSession) {
        SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails customUserDetails) {
                int userId = customUserDetails.getId();
                String username = customUserDetails.getUsername();
                String name = customUserDetails.getName();
                String password = customUserDetails.getPassword();
                return new User(userId, username, name, password);
            }
        }
        return new User(-1, "empty", "empty", "empty");
    }
}
