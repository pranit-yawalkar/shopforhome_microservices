package com.wipro.shopforhome.orderservice.dto.order;

import java.util.Date;

import com.sun.istack.NotNull;
import com.wipro.shopforhome.orderservice.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor 
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
}
