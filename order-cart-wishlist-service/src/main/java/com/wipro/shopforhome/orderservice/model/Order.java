package com.wipro.shopforhome.orderservice.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Order Model Class in which attributes 
 * and methods related to Order model are defined.
 * 
 */
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "total_price", nullable = false)
	private Double totalPrice;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ordered_item_id")
	private OrderItem orderItem;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, Date createdDate, Double totalPrice, OrderItem orderItem, User user) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.totalPrice = totalPrice;
		this.orderItem = orderItem;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
