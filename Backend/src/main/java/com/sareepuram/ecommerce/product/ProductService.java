package com.sareepuram.ecommerce.product;

import java.util.ArrayList;
import java.util.List;

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

    public Object findByName(String searchString) {
        Product product = productRepository.findByName(searchString);
        if (product != null)
            return product;
        else
            return (Object) this.findAll();
    }
}
