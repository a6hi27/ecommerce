package com.sareepuram.ecommerce.cart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sareepuram.ecommerce.product.Product;
import com.sareepuram.ecommerce.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class Cart implements Serializable {
    public Cart(User user, Product product, Integer quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    @ManyToOne
    @JsonBackReference(value = "cart_user")
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
}
