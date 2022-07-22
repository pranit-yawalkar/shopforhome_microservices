package com.wipro.shopforhome.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.shopforhome.orderservice.dto.cart.CartDTO;
import com.wipro.shopforhome.orderservice.dto.order.OrderDTO;
import com.wipro.shopforhome.orderservice.dto.order.OrderItemDTO;
import com.wipro.shopforhome.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

//	@PostMapping("/create-checkout-session")
//	public ResponseEntity<StripeResponse> getCheckoutList(@RequestBody List<CheckoutItemDTO> checkoutItemDTOList)
//			throws StripeException {
//		Session session = this.orderService.createSession(checkoutItemDTOList);
//		StripeResponse stripeResponse = new StripeResponse(session.getId());
//		return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
//	}

	@PostMapping("/place-order")
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody CartDTO cartDTO, @RequestParam("token") String token) {
		OrderDTO orderDTO = this.orderService.placeOrder(cartDTO, token);
		return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<OrderItemDTO>> getAllOrders(@RequestParam("token") String token) {
		List<OrderItemDTO> orderDTOList = this.orderService.getAllOrders(token);
		return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
	}

	@GetMapping("/get/{orderId}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId, @RequestParam("token") String token) {
		OrderDTO orderDTO = this.orderService.getOrderById(orderId, token);
		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
	}
}
