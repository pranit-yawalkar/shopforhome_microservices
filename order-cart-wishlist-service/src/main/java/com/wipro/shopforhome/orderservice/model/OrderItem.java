package com.wipro.shopforhome.orderservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * OrderItem Model Class in which attributes 
 * and methods related to OrderItem model are defined.
 * 
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordered_item_id")
	private Long id;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@OneToOne()
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderItem(int quantity, Double price, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

	public OrderItem() {
		super();
	}

	public OrderItem(Long id, int quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
