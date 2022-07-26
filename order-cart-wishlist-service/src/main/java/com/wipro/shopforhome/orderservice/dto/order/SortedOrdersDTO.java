package com.wipro.shopforhome.orderservice.dto.order;

import java.util.Date;
import java.util.List;

import com.sun.istack.NotNull;

/*
 *	SortedOrdersDTO to transfer data in the required 
 *	format to the client side 
 *
 */
public class SortedOrdersDTO {
	private @NotNull Date createdDate;
	private @NotNull List<OrderItemDTO> orderItemDTOs;

	public SortedOrdersDTO() {
		super();
	}

	public SortedOrdersDTO(Date createdDate, List<OrderItemDTO> orderItemDTOs) {
		super();
		this.createdDate = createdDate;
		this.orderItemDTOs = orderItemDTOs;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<OrderItemDTO> getOrderItemDTOs() {
		return orderItemDTOs;
	}

	public void setOrderItemDTOs(List<OrderItemDTO> orderItemDTOs) {
		this.orderItemDTOs = orderItemDTOs;
	}

}
