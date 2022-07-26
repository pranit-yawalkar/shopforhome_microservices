package com.wipro.shopforhome.orderservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Product Model Class in which attributes 
 * and methods related to Product model are defined.
 * 
 */
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "product_name", nullable = false)
	private String productName;

	@Column(name = "image_url", nullable = false)
	private String imageUrl;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Product() {
		super();
	}

	public Product(Long id, String productName, String imageUrl, Double price, String description, Integer quantity,
			Category category) {
		super();
		this.id = id;
		this.productName = productName;
		this.imageUrl = imageUrl;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
