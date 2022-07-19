package com.wipro.shopforhome.wishlistservice.dto;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToWishListDTO {
    private Long id;
    private @NotNull Long productId;
}
