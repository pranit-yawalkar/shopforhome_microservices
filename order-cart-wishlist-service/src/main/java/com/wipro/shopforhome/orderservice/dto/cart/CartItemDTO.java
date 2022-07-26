package com.wipro.shopforhome.orderservice.dto.cart;

import com.sun.istack.NotNull;
import com.wipro.shopforhome.orderservice.dto.ProductDTO;
import com.wipro.shopforhome.orderservice.model.Cart;

/*
 *	CartItemDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class CartItemDTO {

	private Long id;
	private @NotNull int quantity;
	private ProductDTO productDTO;

	public CartItemDTO(Cart cart, ProductDTO productDTO) {
		this.id = cart.getId();
		this.quantity = cart.getQuantity();
		this.productDTO = productDTO;
	}

	public CartItemDTO() {
		super();
	}

	public CartItemDTO(Long id, int quantity, ProductDTO productDTO) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.productDTO = productDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

}
