package com.sareepuram.ecommerce.order;

import com.sareepuram.ecommerce.cart.Cart;
import com.sareepuram.ecommerce.cart.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private List<CartDTO> cartDTO;

    public OrderDTO(List<CartDTO> cartDTO) {
        this.cartDTO = cartDTO;
    }
}
