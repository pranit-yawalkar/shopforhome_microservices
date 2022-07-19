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
import com.wipro.shopforhome.orderservice.model.Product;
import com.wipro.shopforhome.orderservice.model.User;
import com.wipro.shopforhome.orderservice.repository.CartRepository;

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

//    @Autowired
//    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;


    public Product getProductByProductDTO(ProductDTO productDTO) {
        Category category = this.restTemplate.getForObject(CATEGORY_URI + productDTO.getCategoryId(), Category.class);
        Product product = new Product(productDTO.getProductId(), productDTO.getProductName(),
                productDTO.getImageUrl(), productDTO.getPrice(), productDTO.getDescription(), productDTO.getQuantity(), category);
        return product;
    }

    public Cart addToCart(AddToCartDTO addToCartDTO, String token) {

        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        // validate if the product id is valid
//

        ProductDTO productDTO = this.restTemplate.getForObject(PRODUCT_URI + addToCartDTO.getProductId(), ProductDTO.class);
        Category category = this.restTemplate.getForObject(CATEGORY_URI + productDTO.getCategoryId(), Category.class);
        Product product = new Product(productDTO.getProductId(), productDTO.getProductName(),
                productDTO.getImageUrl(), productDTO.getPrice(), productDTO.getDescription(), productDTO.getQuantity(), category);

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

        for(Cart cart: cartList) {
//            ProductDTO productDTO = this.productService.getProductDTO(cart.getProduct());
            ProductDTO productDTO = this.restTemplate.getForObject(
                    PRODUCT_URI + cart.getProduct().getId(), ProductDTO.class);
            CartItemDTO cartItemDTO = new CartItemDTO(cart, productDTO);
            totalCost += (cartItemDTO.getQuantity() *cartItemDTO.getProductDTO().getPrice());
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

        for(Cart cart: cartList) {
            ProductDTO productDTO = this.restTemplate.getForObject(
                    PRODUCT_URI + cart.getProduct().getId(), ProductDTO.class);
            CartItemDTO cartItemDTO = new CartItemDTO(cart, productDTO);
            totalCost += (cartItemDTO.getQuantity() *cartItemDTO.getProductDTO().getPrice());
            cartItems.add(cartItemDTO);
        }

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItems(cartItems);
        cartDTO.setTotalCost(totalCost);

        return cartDTO;
    }

    public List<CartItemDTO> getCartItemDTO(User user) {
        List<Cart> cartList = this.cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDTO> cartItems = new ArrayList<>();
        for(Cart cart: cartList) {
            ProductDTO productDTO = this.restTemplate.getForObject(
                    PRODUCT_URI + cart.getProduct().getId(), ProductDTO.class);
            CartItemDTO cartItemDTO = new CartItemDTO(cart, productDTO);
            cartItems.add(cartItemDTO);
        }
        return cartItems;
    }

    public void deleteCartItem(Long cartItemId, String token) {
        this.authenticationService.authenticate(token);

        User user = this.authenticationService.getUser(token);

        Cart cart = this.cartRepository.findById(cartItemId)
                .orElseThrow(()->new ResourceNotFoundException("Cart item id is invalid"));

        if(cart.getUser()!=user) {
            throw new CustomException("Cart item does not belong to user");
        }

        this.cartRepository.delete(cart);
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }
}
