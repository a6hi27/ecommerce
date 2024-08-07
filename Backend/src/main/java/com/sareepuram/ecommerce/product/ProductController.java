package com.sareepuram.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("product")
    public ResponseEntity<?> getProducts(@RequestParam(required = false) String searchQuery,
                                         @RequestParam(required = false) Integer id) {

        // Gets products using the searchQuery
        if (searchQuery != null) {
            Object product = productService.findByName(searchQuery.toLowerCase());
            if (product instanceof Product)
                return ResponseEntity.status(HttpStatus.OK).body((Product) product);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(product);

        }

        // Gets products using the product id
        if (id != null) {
            Product product = productService.findById(Integer.valueOf(id));
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }

        // If no query parameters are found in URL, returns all the products
        List<Product> products = productService.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    //add a product to product table
    @PostMapping("product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("product")
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product) {
        Integer isDeleted = productService.deleteProduct(product);
        System.out.println("The number of rows deleted is" + isDeleted);
        if (isDeleted > 0)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}