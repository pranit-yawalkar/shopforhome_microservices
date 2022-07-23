package com.wipro.shopforhome.orderservice.dto.order;

import java.util.Date;
import java.util.List;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortedOrdersDTO {
	private @NotNull Date createdDate;
	private @NotNull List<OrderItemDTO> orderItemDTOs;
}
