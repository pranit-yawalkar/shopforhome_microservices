package com.wipro.shopforhome.orderservice.dto.order;

import java.util.Date;

import com.sun.istack.NotNull;
import com.wipro.shopforhome.orderservice.model.OrderItem;

/*
 *	OrderItemDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class OrderItemDTO {
	private @NotNull Long id;
	private @NotNull Date createdDate;
	private @NotNull Double price;
	private @NotNull OrderItem orderItem;

	public OrderItemDTO(Long id, Double price, OrderItem orderItem) {
		this.id = id;
		this.createdDate = new Date();
		this.price = price;
		this.orderItem = orderItem;
	}

	public OrderItemDTO() {
		super();
	}

	public OrderItemDTO(Long id, Date createdDate, Double price, OrderItem orderItem) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.price = price;
		this.orderItem = orderItem;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

}
