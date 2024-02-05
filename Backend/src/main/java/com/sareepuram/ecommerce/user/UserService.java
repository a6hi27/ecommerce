package com.sareepuram.ecommerce.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Get all users from User table
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public Object findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null)
            return user;
        else
            return "User not found for the email " + email;
    }

    // Add a user to the User table
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Update a user
    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
