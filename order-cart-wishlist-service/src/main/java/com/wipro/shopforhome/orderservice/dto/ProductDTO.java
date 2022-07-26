package com.wipro.shopforhome.orderservice.dto;

import com.sun.istack.NotNull;

/*
 *	ProductDTO to transfer data in the required 
 *	format to the client side 
 *
 */

public class ProductDTO {
	private @NotNull Long productId;
	private @NotNull String productName;
	private @NotNull String imageUrl;
	private @NotNull Double price;
	private @NotNull String description;
	private @NotNull Integer quantity;
	private @NotNull Long categoryId;

	public ProductDTO() {
		super();
	}

	public ProductDTO(Long productId, String productName, String imageUrl, Double price, String description,
			Integer quantity, Long categoryId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.categoryId = categoryId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

}