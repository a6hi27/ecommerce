package com.sareepuram.ecommerce.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sareepuram.ecommerce.cart.Cart;
import com.sareepuram.ecommerce.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "`order`")
@Data
@NoArgsConstructor
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_cart", joinColumns = {@JoinColumn(name = "order_id")}, inverseJoinColumns =
    @JoinColumn(name = "cart_id"))
    @JsonManagedReference(value = "order_cart")
    private List<Cart> productsInOrder;

    public Order(User user, List<Cart> products) {
        this.user = user;
        this.productsInOrder = products;
    }
}
