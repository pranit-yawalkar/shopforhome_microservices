package com.wipro.shopforhome.orderservice.dto.order;

import java.util.Date;
import java.util.List;
import com.sun.istack.NotNull;
import com.wipro.shopforhome.orderservice.model.OrderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private @NotNull Long id;
	private @NotNull Date createdDate;
	private @NotNull Double totalPrice;
	private @NotNull List<OrderItem> orderItems;
	private @NotNull Long userId;
}
