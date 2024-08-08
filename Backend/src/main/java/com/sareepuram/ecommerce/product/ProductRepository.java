package com.sareepuram.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String searchString);

    Integer deleteByProductId(Integer productId);
}
