package com.wipro.shopforhome.orderservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Cart Model Class in which attributes 
 * and methods related to Cart model are defined.
 * 
 */

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Cart(int quantity, Product product, User user) {
		this.quantity = quantity;
		this.product = product;
		this.user = user;
		this.createdDate = new Date();
	}

	public Cart() {
		super();
	}

	public Cart(Long id, Date createdDate, int quantity, Product product, User user) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.quantity = quantity;
		this.product = product;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
