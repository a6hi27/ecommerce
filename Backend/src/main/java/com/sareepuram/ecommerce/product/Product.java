package com.sareepuram.ecommerce.product;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product implements Serializable {
    @Id
    private int productId;
    private String imageUrl;
    private String name;
    private String price;
    private String size;

}
