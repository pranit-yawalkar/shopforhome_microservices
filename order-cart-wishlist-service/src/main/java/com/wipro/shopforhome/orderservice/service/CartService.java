package com.wipro.shopforhome.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.shopforhome.orderservice.dto.ProductDTO;
import com.wipro.shopforhome.orderservice.dto.cart.AddToCartDTO;
import com.wipro.shopforhome.orderservice.dto.cart.CartDTO;
import com.wipro.shopforhome.orderservice.dto.cart.CartItemDTO;
import com.wipro.shopforhome.orderservice.exception.CustomException;
import com.wipro.shopforhome.orderservice.exception.ResourceNotFoundException;
import com.wipro.shopforhome.orderservice.model.Cart;
import com.wipro.shopforhome.orderservice.model.Category;
import com.wipro.shopforhome.orderservice.model.Coupon;
import com.wipro.shopforhome.orderservice.model.Product;
import com.wipro.shopforhome.orderservice.model.User;
import com.wipro.shopforhome.orderservice.repository.CartRepository;

/*
 * Cart Service class to define the business logic and interact with the 
 * Cart Repository, Product Service and Discount Service.
 */
@Service
@RefreshScope
public class CartService {

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	@Autowired
	private CartRepository cartRepository;

	@Value("${microservice.product-service.endpoints.endpoint.uri}")
	private String PRODUCT_URI;

	@Value("${microservice.category-service.endpoints.endpoint.uri}")
	private String CATEGORY_URI;

	@Value("${microservice.discount-service.endpoints.endpoint.uri}")
	private String DISCOUNT_URI;

	@Autowired
	private AuthenticationService authenticationService;

	public Product getProductByProductDTO(ProductDTO productDTO) {
		Category category = this.restTemplate.getForObject(CATEGORY_URI + productDTO.getCategoryId(), Category.class);
		Product product = new Product(productDTO.getProductId(), productDTO.getProductName(), productDTO.getImageUrl(),
				productDTO.getPrice(), productDTO.getDescription(), productDTO.getQuantity(), category);
		return product;
	}

	public boolean setQuantityById(String token, Long id, Integer quantity) {
		authenticationService.authenticate(token);

		User user = authenticationService.getUser(token);

		CartItemDTO cartItemDTO = this.getCartItemById(id);
		cartItemDTO.setQuantity(quantity);
		return true;
	}

	public Cart addToCart(AddToCartDTO addToCartDTO, String token) {

		authenticationService.authenticate(token);

		User user = authenticationService.getUser(token);

		// check if product already exist in cart
		CartDTO cartDTO = getCartItems(user);

		cartDTO.getCartItems().forEach(cartItem -> {
			if (cartItem.getProductDTO().getProductId().equals(addToCartDTO.getProductId())) {
				throw new CustomException("Product already added to the cart");
			}
		});

		// validate if the product id is valid
		ProductDTO productDTO = this.restTemplate.getForObject(PRODUCT_URI + addToCartDTO.getProductId(),
				ProductDTO.class);
		Category category = this.restTemplate.getForObject(CATEGORY_URI + productDTO.getCategoryId(), Category.class);
		Product product = new Product(productDTO.getProductId(), productDTO.getProductName(), productDTO.getImageUrl(),
				productDTO.getPrice(), productDTO.getDescription(), productDTO.getQuantity(), category);

		Cart cart = new Cart(addToCartDTO.getQuantity(), product, user);

		// save the cart
		return this.cartRepository.save(cart);
	}

	public CartDTO getCartItems(String token) {

		this.authenticationService.authenticate(token);

		User user = this.authenticationService.getUser(token);

		List<Cart> cartList = this.cartRepository.findAllByUserOrderByCreatedDateDesc(user);

		List<CartItemDTO> cartItems = new ArrayList<>();

		double totalCost = 0;

		for (Cart cart : cartList) {
			ProductDTO productDTO = this.restTemplate.getForObject(PRODUCT_URI + cart.getProduct().getId(),
					ProductDTO.class);
			CartItemDTO cartItemDTO = new CartItemDTO(cart, productDTO);
			totalCost += (cartItemDTO.getQuantity() * cartItemDTO.getProductDTO().getPrice());
			cartItems.add(cartItemDTO);
		}

		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartItems(cartItems);
		cartDTO.setTotalCost(totalCost);

		return cartDTO;
	}

	public CartDTO getCartItems(User user) {
		List<Cart> cartList = this.cartRepository.findAllByUserOrderByCreatedDateDesc(user);

		List<CartItemDTO> cartItems = new ArrayList<>();

		double totalCost = 0;

		for (Cart cart : cartList) {
			ProductDTO productDTO = this.restTemplate.getForObject(PRODUCT_URI + cart.getProduct().getId(),
					ProductDTO.class);
			CartItemDTO cartItemDTO = new CartItemDTO(cart, productDTO);
			totalCost += (cartItemDTO.getQuantity() * cartItemDTO.getProductDTO().getPrice());
			cartItems.add(cartItemDTO);
		}

		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartItems(cartItems);
		cartDTO.setTotalCost(totalCost);

		return cartDTO;
	}

	public CartItemDTO getCartItemById(Long cartItemId) {

		Cart cart = this.cartRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart item id is invalid"));
		CartItemDTO cartItemDTO = this.getCartItemDTO(cart);
		return cartItemDTO;
	}

	public List<CartItemDTO> getCartItemDTO(User user) {
		List<Cart> cartList = this.cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDTO> cartItems = new ArrayList<>();
		for (Cart cart : cartList) {
			CartItemDTO cartItemDTO = this.getCartItemDTO(cart);
			cartItems.add(cartItemDTO);
		}
		return cartItems;
	}

	public CartItemDTO getCartItemDTO(Cart cart) {
		ProductDTO productDTO = this.restTemplate.getForObject(PRODUCT_URI + cart.getProduct().getId(),
				ProductDTO.class);
		CartItemDTO cartItemDTO = new CartItemDTO(cart, productDTO);
		return cartItemDTO;
	}

	public void updateCartItem(Long CartItemId, AddToCartDTO addToCartDTO, String token) {
		authenticationService.authenticate(token);

		User user = authenticationService.getUser(token);

		Cart cart = this.cartRepository.findById(CartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart does not exist"));
		cart.setQuantity(addToCartDTO.getQuantity());
		this.cartRepository.save(cart);
	}

	public void deleteCartItem(Long cartItemId, String token) {
		this.authenticationService.authenticate(token);

		User user = this.authenticationService.getUser(token);

		Cart cart = this.cartRepository.findById(cartItemId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart item id is invalid"));

		if (cart.getUser() != user) {
			throw new CustomException("Cart item does not belong to user");
		}

		this.cartRepository.delete(cart);
	}

	public void deleteAll(String token) {
		this.authenticationService.authenticate(token);

		User user = this.authenticationService.getUser(token);

		this.cartRepository.deleteAll();
	}

	public void deleteUserCartItems(User user) {
		cartRepository.deleteByUser(user);
	}

	public CartDTO applyCoupon(Coupon coupon, String token) {
		this.authenticationService.authenticate(token);
		User user = this.authenticationService.getUser(token);
		CartDTO cartDTO = this.getCartItems(user);
		double totalcost = cartDTO.getTotalCost();
		double discount = (totalcost * (coupon.getPercentage() * 0.01));
		;
		cartDTO.setDiscount(discount);
		this.restTemplate.delete(DISCOUNT_URI + "/delete/" + coupon.getId() + "?role=admin");
		return cartDTO;
	}
}
