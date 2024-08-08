package com.sareepuram.ecommerce.order;

import com.sareepuram.ecommerce.cart.Cart;
import com.sareepuram.ecommerce.cart.CartRepository;
import com.sareepuram.ecommerce.product.Product;
import com.sareepuram.ecommerce.cart.CartDTO;
import com.sareepuram.ecommerce.product.ProductRepository;
import com.sareepuram.ecommerce.user.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<List<Cart>> getOrders(Integer userId) {
        Optional<List<Order>> orders = orderRepository.findByUser_UserId(userId);
        if(orders.isPresent()){
           List<List<Cart>> productsInOrder = orders.get().stream().map(order -> order.getProductsInOrder()).toList();
           return productsInOrder;
        }
        return Collections.emptyList();
    }

    public Order addOrder(User user) {
        List<Cart> productsInOrder = cartRepository.findAllByUser_UserId(user.getUserId());
        return orderRepository.save(new Order(user, productsInOrder));
    }
}
