package com.wipro.shopforhome.orderservice.dto.wishlist;


import java.util.Date;

import com.wipro.shopforhome.orderservice.dto.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListItemDTO {
	private Long id;
	private ProductDTO productDTO;
	private Date createdDate;

	public WishListItemDTO(Long id, ProductDTO productDTO) {
		this.id = id;
		this.productDTO = productDTO;
		this.createdDate = new Date();
	}
}
