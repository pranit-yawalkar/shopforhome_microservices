package com.wipro.shopforhome.wishlistservice.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
    private Long id;
    private ProductDTO productDTO;
    private Date createdDate;

    public WishListDTO(Long id, ProductDTO productDTO) {
        this.id = id;
        this.productDTO = productDTO;
        this.createdDate = new Date();
    }
}
