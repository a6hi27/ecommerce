package com.sareepuram.ecommerce.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.Collections;

import com.sareepuram.ecommerce.product.Product;
import com.sareepuram.ecommerce.product.ProductService;
import com.sareepuram.ecommerce.user.User;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    public CartDTO addOrUpdateProductInCart(CartDTO cartDTO, User user) {
        Integer userId = user.getUserId();
        Integer productId = cartDTO.getProduct().getProductId();
        Integer quantity = cartDTO.getQuantity();
        Optional<Cart> cart = cartRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
        if (cart.isPresent()) {
            // Update existing cart entry
            Cart existingCart = cart.get();
            existingCart.setQuantity(cartDTO.getQuantity());
            existingCart = cartRepository.save(existingCart);
            CartDTO existingCartDTO = new CartDTO(existingCart.getProduct(), existingCart.getQuantity());
            return existingCartDTO;
        }
        // Create new cart entry
        Product product = productService.findById(productId);
        Cart newCart = new Cart(user, product, quantity);
        newCart = cartRepository.save(newCart);
        CartDTO newCartDTO = new CartDTO(newCart.getProduct(), newCart.getQuantity());
        return newCartDTO;
    }

    public List<CartDTO> getProductsInCart(User user) {
        Integer userId = user.getUserId();
        Optional<List<CartDTO>> productsInCart = cartRepository.findProductsInCartByUserId(userId);
        return productsInCart.orElse(Collections.emptyList());
    }
}
