package com.sareepuram.ecommerce.order;

import com.sareepuram.ecommerce.cart.Cart;
import com.sareepuram.ecommerce.user.User;
import com.sareepuram.ecommerce.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("user/order")
    public ResponseEntity<List<List<Cart>>> getProductsInOrder(HttpSession httpSession) {
        Integer userId = userService.getCurrentUser(httpSession).getUserId();
        return new ResponseEntity<>(orderService.getOrders(userId), HttpStatus.OK);
    }

    @PostMapping("user/order")
    public ResponseEntity<Order> addOrder(HttpSession httpSession) {
        User user = userService.getCurrentUser(httpSession);
        return new ResponseEntity<>(orderService.addOrder(user), HttpStatus.OK);
    }
}
