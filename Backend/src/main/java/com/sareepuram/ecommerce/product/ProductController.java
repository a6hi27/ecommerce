package com.sareepuram.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        System.out.println(products.toString());
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("products/{searchString}")
    public ResponseEntity<?> findByName(@PathVariable String searchString) {
        Object product = productService.findByName(searchString);
        if (product instanceof Product)
            return ResponseEntity.status(HttpStatus.OK).body((Product) product);
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(product);
        }
    }

}