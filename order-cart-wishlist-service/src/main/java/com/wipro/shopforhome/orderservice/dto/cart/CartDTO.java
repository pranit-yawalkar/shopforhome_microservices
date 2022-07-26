package com.wipro.shopforhome.orderservice.dto.cart;

import java.util.List;

/*
 *	CartDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class CartDTO {
	private List<CartItemDTO> cartItems;
	private Double totalCost;
	private Double discount;

	public CartDTO() {
		super();
	}

	public CartDTO(List<CartItemDTO> cartItems, Double totalCost, Double discount) {
		super();
		this.cartItems = cartItems;
		this.totalCost = totalCost;
		this.discount = discount;
	}

	public List<CartItemDTO> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDTO> cartItems) {
		this.cartItems = cartItems;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
