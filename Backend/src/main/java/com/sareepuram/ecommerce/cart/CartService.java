package com.sareepuram.ecommerce.cart;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.Collections;

import com.sareepuram.ecommerce.product.Product;
import com.sareepuram.ecommerce.product.ProductService;
import com.sareepuram.ecommerce.user.User;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

//    @CachePut(value = "cartCache", key = "#user.userId + '_' + #cartDTO.product.productId")
    public CartDTO addOrUpdateProductInCart(CartDTO cartDTO, User user) {
        Integer userId = user.getUserId();
        Integer productId = cartDTO.getProduct().getProductId();
        Integer quantity = cartDTO.getQuantity();
        Optional<Cart> cart = cartRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
        if (cart.isPresent()) {
            // Update existing product in cart
            Cart existingCart = cart.get();
            existingCart.setQuantity(cartDTO.getQuantity());
            existingCart = cartRepository.save(existingCart);
            CartDTO existingCartDTO = new CartDTO(existingCart.getProduct(), existingCart.getQuantity());
            return existingCartDTO;
        }
        // Add the new product in the cart
        Product product = productService.findById(productId);
        Cart newCart = new Cart(user, product, quantity);
        newCart = cartRepository.save(newCart);
        CartDTO newCartDTO = new CartDTO(newCart.getProduct(), newCart.getQuantity());
        return newCartDTO;
    }

//    @Cacheable(value = "cartCache", key = "#user.userId")
    public List<CartDTO> getProductsInCart(User user) {
        Integer userId = user.getUserId();
        Optional<List<CartDTO>> productsInCart = cartRepository.findProductsInCartByUserId(userId);
        System.out.println("This is from DB");
        return productsInCart.orElse(Collections.emptyList());
    }

//    @CacheEvict(value = "cartCache", key = "#user.userId + '_' + #cartDTO.product.productId")
    public ResponseEntity<?> deleteProductFromCart(CartDTO cartDTO, User user) {
        Integer userId = user.getUserId();
        Integer productId = cartDTO.getProduct().getProductId();
        Integer isDeleted = cartRepository.deleteByUser_UserIdAndProduct_ProductId(userId, productId);
        if (isDeleted > 0)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
