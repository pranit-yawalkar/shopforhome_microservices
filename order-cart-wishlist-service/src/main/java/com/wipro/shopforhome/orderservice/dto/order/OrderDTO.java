package com.wipro.shopforhome.orderservice.dto.order;

import java.util.Date;

import com.sun.istack.NotNull;
import com.wipro.shopforhome.orderservice.model.OrderItem;

/*
 *	OrderDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class OrderDTO {
	private @NotNull Long id;
	private @NotNull Date createdDate;
	private @NotNull Double totalPrice;
	private @NotNull OrderItem orderItem;
	private @NotNull Long userId;

	public OrderDTO() {
		super();
	}

	public OrderDTO(Long id, Date createdDate, Double totalPrice, OrderItem orderItem, Long userId) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.totalPrice = totalPrice;
		this.orderItem = orderItem;
		this.userId = userId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
