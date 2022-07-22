package com.wipro.shopforhome.orderservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.shopforhome.orderservice.dto.ProductDTO;
import com.wipro.shopforhome.orderservice.dto.cart.CartDTO;
import com.wipro.shopforhome.orderservice.dto.cart.CartItemDTO;
import com.wipro.shopforhome.orderservice.dto.order.OrderDTO;
import com.wipro.shopforhome.orderservice.dto.order.OrderItemDTO;
import com.wipro.shopforhome.orderservice.exception.ResourceNotFoundException;
import com.wipro.shopforhome.orderservice.model.Order;
import com.wipro.shopforhome.orderservice.model.OrderItem;
import com.wipro.shopforhome.orderservice.model.Product;
import com.wipro.shopforhome.orderservice.model.User;
import com.wipro.shopforhome.orderservice.repository.OrderItemRepository;
import com.wipro.shopforhome.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CartService cartService;

//    @Autowired
//    private ProductService productService;

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

//    @Value("${BASE_URL}")
//    private String baseURL;
//
//    @Value("${STRIPE_SECRET_KEY}")
//    private String apiKey;

//    public Session createSession(List<CheckoutItemDTO> checkoutItemDTOList) throws StripeException {
//        // success and failure urls that will be created using frontend
//        String successURL = baseURL + "payment/success";
//        String failureURL = baseURL + "payment/failure";
//
//        Stripe.apiKey=apiKey;
//
//        List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();
//
//        checkoutItemDTOList.forEach(checkoutItemDTO -> {
//            sessionItemList.add(createSessionLineItem(checkoutItemDTO));
//        });
//
//        SessionCreateParams params = SessionCreateParams.builder()
//                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setCancelUrl(failureURL)
//                .setSuccessUrl(successURL)
//                .addAllLineItem(sessionItemList)
//                .build();
//        return Session.create(params);
//    }
//
//    private SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDTO checkoutItemDTO) {
//        return SessionCreateParams.LineItem.builder()
//                .setPriceData(createPriceData(checkoutItemDTO))
//                .setQuantity((long) checkoutItemDTO.getQuantity())
//                .build();
//    }
//
//    private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDTO checkoutItemDTO) {
//        return SessionCreateParams.LineItem.PriceData.builder()
//                .setCurrency("usd")
//                .setUnitAmount((long) (checkoutItemDTO.getPrice()*100))
//                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                        .setName(checkoutItemDTO.getProductName())
//                        .build()
//                ).build();
//    }

	public OrderDTO getOrderDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setTotalPrice(order.getTotalPrice());
//		orderDTO.setOrderItems(order.getOrderItems());
		orderDTO.setUserId(order.getUser().getId());
		orderDTO.setCreatedDate(new Date());
		return orderDTO;
	}

	@Transactional
	public OrderDTO placeOrder(CartDTO cartDTO, String token) {
		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);

		List<CartItemDTO> cartItemsDtoList = cartDTO.getCartItems();
		List<OrderItem> orderItems = new ArrayList<>();

		Order newOrder = new Order();
		cartItemsDtoList.forEach(cartItemDTO -> {
			ProductDTO productDTO = this.restTemplate.getForObject(
					"http://product-service/api/product/get/" + cartItemDTO.getProductDTO().getProductId(),
					ProductDTO.class);
			Product product = null;
			if (productDTO != null) {
				product = this.cartService.getProductByProductDTO(productDTO);
			}
			
			OrderItem orderItem = new OrderItem(cartItemDTO.getQuantity(), cartItemDTO.getProductDTO().getPrice(), product);
			orderItemRepository.save(orderItem);
			newOrder.setCreatedDate(new Date());
			newOrder.setUser(user);
			newOrder.setOrderItem(orderItem);
			newOrder.setTotalPrice(cartDTO.getTotalCost());
			orderRepository.save(newOrder);
		});

		this.cartService.deleteUserCartItems(user);

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(newOrder.getId());
		orderDTO.setCreatedDate(new Date());
		orderDTO.setOrderItems(orderItems);
		orderDTO.setTotalPrice(newOrder.getTotalPrice());
		orderDTO.setUserId(newOrder.getUser().getId());

		return orderDTO;
	}

	@Transactional
	public List<OrderItemDTO> getAllOrders(String token) {

		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);

		List<Order> orderList = this.orderRepository.findByUser(user);
		List<OrderItemDTO> orderDTOs = new ArrayList<>();

		orderList.forEach(order -> {
			OrderItemDTO orderItemDTO = new OrderItemDTO(order.getOrderItem().getId(), 
					order.getTotalPrice(), order.getOrderItem());
			orderDTOs.add(orderItemDTO);
		});
		
		return orderDTOs;
	}

	@Transactional
	public OrderDTO getOrderById(Long orderId, String token) {
		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order does not exist"));
		return getOrderDTO(order);
	}
	
	public OrderDTO getOrderById(Long orderId) {
		Order order = this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order does not exist"));
		return getOrderDTO(order);
	}

}
