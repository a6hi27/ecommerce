package com.sareepuram.ecommerce.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> goToHome() {
        return (ResponseEntity.ok("Welcome to my homepage"));
    }

    @GetMapping("user")
    public ResponseEntity<List<User>> findAll() {
        List<User> user = userService.findAll();
//        List<UserDTO> userDTO = userService.findAll().stream().map(user -> new UserDTO(user.getUserId(), user.getName(), user.getEmail())).collect(Collectors.toList());
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
    }

    @PutMapping("user/{id}")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}