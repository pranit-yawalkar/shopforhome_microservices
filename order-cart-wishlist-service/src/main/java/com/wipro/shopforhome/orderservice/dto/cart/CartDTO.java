package com.wipro.shopforhome.orderservice.dto.cart;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private List<CartItemDTO> cartItems;
    private Double totalCost;
}

