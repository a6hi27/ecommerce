package com.sareepuram.ecommerce.cart;

import java.util.List;

import com.sareepuram.ecommerce.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sareepuram.ecommerce.user.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("user/cart")
    public ResponseEntity<List<CartDTO>> getProductsInCart(HttpSession httpSession) {
        List<CartDTO> products = cartService.getProductsInCart(userService.getCurrentUser(httpSession));
        return new ResponseEntity<List<CartDTO>>(products, HttpStatus.OK);
    }

    @PostMapping("user/cart")
    public ResponseEntity<CartDTO> addorUpdateProductInCart(@RequestBody CartDTO cartDTO, HttpSession httpSession) {
        return new ResponseEntity<CartDTO>(cartService.addOrUpdateProductInCart(cartDTO,
                userService.getCurrentUser(httpSession)),
                HttpStatus.OK);
    }
}
