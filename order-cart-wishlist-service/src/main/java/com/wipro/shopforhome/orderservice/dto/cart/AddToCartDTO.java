package com.wipro.shopforhome.orderservice.dto.cart;

import com.sun.istack.NotNull;

/*
 *	AddToCartDTO to transfer data in the required 
 *	format to the client side 
 *
 */

public class AddToCartDTO {
	private Long id;
	private @NotNull Long productId;
	private @NotNull int quantity;

	public AddToCartDTO() {
		super();
	}

	public AddToCartDTO(Long id, Long productId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
