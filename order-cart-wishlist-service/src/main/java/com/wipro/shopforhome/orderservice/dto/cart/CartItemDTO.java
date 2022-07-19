package com.wipro.shopforhome.orderservice.dto.cart;


import com.sun.istack.NotNull;
import com.wipro.shopforhome.orderservice.dto.ProductDTO;
import com.wipro.shopforhome.orderservice.model.Cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;
    private @NotNull int quantity;
    private ProductDTO productDTO;

    public CartItemDTO(Cart cart, ProductDTO productDTO) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.productDTO = productDTO;
    }

}

