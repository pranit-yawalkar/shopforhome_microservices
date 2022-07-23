package com.wipro.shopforhome.orderservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.wipro.shopforhome.orderservice.dto.order.SortedOrdersDTO;
import com.wipro.shopforhome.orderservice.dto.order.SortedOrdersDTO;
import com.wipro.shopforhome.orderservice.exception.CustomException;
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
		orderDTO.setOrderItem(order.getOrderItem());
		orderDTO.setUserId(order.getUser().getId());
		orderDTO.setCreatedDate(new Date());
		return orderDTO;
	}

	@Transactional
	public List<OrderItemDTO> placeOrder(CartDTO cartDTO, String token) {
		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);

		List<CartItemDTO> cartItemsDtoList = cartDTO.getCartItems();
		List<OrderItemDTO> orderItems = new ArrayList<>();

		cartItemsDtoList.forEach(cartItemDTO -> {
//			ProductDTO productDTO = this.restTemplate.getForObject(
//					"http://product-service/api/product/get/" + cartItemDTO.getProductDTO().getProductId(),
//					ProductDTO.class);
			ProductDTO productDTO = cartItemDTO.getProductDTO();
			Product product = null;
			if (productDTO != null) {
				product = this.cartService.getProductByProductDTO(productDTO);
			}
			Order newOrder = new Order();
			OrderItem orderItem = new OrderItem(cartItemDTO.getQuantity(), cartItemDTO.getProductDTO().getPrice(),
					product);
			orderItemRepository.save(orderItem);
			newOrder.setCreatedDate(new Date());
			newOrder.setUser(user);
			newOrder.setOrderItem(orderItem);
			newOrder.setTotalPrice(orderItem.getProduct().getPrice());
			orderRepository.save(newOrder);
			OrderItemDTO orderItemDTO = new OrderItemDTO(orderItem.getId(), orderItem.getProduct().getPrice(),
					orderItem);
			orderItems.add(orderItemDTO);
		});

		this.cartService.deleteUserCartItems(user);

		return orderItems;
	}

	@Transactional
	public List<OrderItemDTO> getAllOrders(String token) {

		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);

		List<Order> orderList = this.orderRepository.findByUser(user);
		List<OrderItemDTO> orderDTOs = new ArrayList<>();

		orderList.forEach(order -> {
			OrderItemDTO orderItemDTO = new OrderItemDTO(order.getOrderItem().getId(), order.getTotalPrice(),
					order.getOrderItem());
			orderDTOs.add(orderItemDTO);
		});

		return orderDTOs;
	}

	@Transactional
	public OrderDTO getOrderById(Long orderId, String token) {
		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);
		Order order = (Order) this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order does not exist"));
		return getOrderDTO(order);
	}

	public OrderDTO getOrderById(Long orderId) {
		Order order = (Order) this.orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order does not exist"));
		return getOrderDTO(order);
	}

	public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	public List<SortedOrdersDTO> getOrdersByCreatedDate() {
		String startDate = "18-07-2022";
		String endDate = "30-07-2022";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date eDate = null;
		Date sDate = null;
		try {
			sDate = formatter.parse(startDate);
			eDate = formatter.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Date> dates = getDaysBetweenDates(sDate, eDate);
		List<SortedOrdersDTO> sortedOrdersDTOs = new ArrayList<>();
		dates.forEach(date -> {
			SortedOrdersDTO sortedOrdersDTO = new SortedOrdersDTO();
			sortedOrdersDTO.setCreatedDate(date);
			List<Order> orders = this.orderRepository.findAllByOrderByCreatedDateDesc();
			List<OrderItemDTO> orderDTOs = new ArrayList<>();
			orders.forEach(order -> {
				OrderItemDTO orderItemDTO = new OrderItemDTO(order.getOrderItem().getId(), order.getTotalPrice(),
						order.getOrderItem());
				orderDTOs.add(orderItemDTO);
			});
			sortedOrdersDTO.setOrderItemDTOs(orderDTOs);
		});
		return sortedOrdersDTOs;
	}

	public List<OrderDTO> getAllSorted(String role) {
		if (role.equals("admin")) {
			List<OrderDTO> orderDTOs = new ArrayList<>();
			List<Order> orderList = this.orderRepository.findAllByOrderByCreatedDateDesc();
			orderList.forEach(order -> {
				OrderDTO orderDTO = getOrderDTO(order);
				orderDTOs.add(orderDTO);
			});

			return orderDTOs;
		} else {
			throw new CustomException("Access Denied!");
		}
	}

	public void deleteOrder(Long orderId, String role) {
		if (role.equals("admin")) {
			Order order = (Order) this.orderRepository.findById(orderId)
					.orElseThrow(() -> new ResourceNotFoundException("Order does not exist"));

			this.orderItemRepository.deleteById(order.getOrderItem().getId());
			this.orderRepository.delete(order);
		} else {
			throw new CustomException("Access Denied!");
		}
	}

}
