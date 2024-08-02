package com.sareepuram.ecommerce.user;

import lombok.Data;

@Data
public class UserDTO {
    private int userId;
    private String name;
    private String email;

    // Constructors, Getters, and Setters
    public UserDTO(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
