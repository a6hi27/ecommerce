package com.sareepuram.ecommerce.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    // Get products by searchQuery
    public Object findByName(String searchString) {
        Product product = productRepository.findByName(searchString);
        if (product != null)
            return product;
        else
            return this.findAll();
    }

    // Get a product using productId
    public Product findById(Integer productId) {
        Optional<Product> products = productRepository.findById(productId);
        return products.orElse(new Product());
    }

    //Add a product to product table
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
