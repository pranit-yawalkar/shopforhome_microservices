package com.wipro.shopforhome.orderservice.dto.wishlist;

import com.sun.istack.NotNull;

/*
 *	AddToWishListDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class AddToWishListDTO {
	private Long id;
	private @NotNull Long productId;

	public AddToWishListDTO() {
		super();
	}

	public AddToWishListDTO(Long id, Long productId) {
		super();
		this.id = id;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
