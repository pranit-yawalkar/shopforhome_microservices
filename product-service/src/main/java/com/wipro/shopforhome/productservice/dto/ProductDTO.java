package com.wipro.shopforhome.productservice.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	private @NotNull Long productId;
	private @NotNull String productName;
	private @NotNull String imageUrl;
	private @NotNull Double price;
	private @NotNull String description;
	private @NotNull Integer quantity;
	private @NotNull Long categoryId;
}
