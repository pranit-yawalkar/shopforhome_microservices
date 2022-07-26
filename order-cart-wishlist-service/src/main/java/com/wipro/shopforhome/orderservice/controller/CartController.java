package com.wipro.shopforhome.orderservice.controller;

import com.wipro.shopforhome.orderservice.dto.ResponseDTO;
import com.wipro.shopforhome.orderservice.dto.cart.AddToCartDTO;
import com.wipro.shopforhome.orderservice.dto.cart.CartDTO;
import com.wipro.shopforhome.orderservice.model.Cart;
import com.wipro.shopforhome.orderservice.model.Coupon;
import com.wipro.shopforhome.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Cart Controller to handle all the requests coming from the 
 * client side and to provide a required response.
 * 
 */
@RestController
@RequestMapping("/api/cart/")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public ResponseEntity<Cart> addToCart(@RequestBody AddToCartDTO addToCartDTO, @RequestParam("token") String token) {

		Cart cart = this.cartService.addToCart(addToCartDTO, token);
		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}

	@GetMapping("/getAll/{token}")
	public ResponseEntity<CartDTO> getCartItems(@PathVariable String token) {
		CartDTO cartDTO = this.cartService.getCartItems(token);
		return new ResponseEntity<>(cartDTO, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ResponseDTO> deleteCartItem(@PathVariable Long cartItemId,
			@RequestParam("token") String token) {
		this.cartService.deleteCartItem(cartItemId, token);
		ResponseDTO responseDTO = new ResponseDTO(true, "Item deleted successfully");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll")
	public ResponseEntity<ResponseDTO> deleteAll(@RequestParam("token") String token) {
		this.cartService.deleteAll(token);
		ResponseDTO responseDTO = new ResponseDTO(true, "Items deleted successfully");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PutMapping("/edit/{cartItemId}")
	public ResponseEntity<ResponseDTO> updateCartItem(@PathVariable Long cartItemId,
			@RequestBody AddToCartDTO addToCartDTO, @RequestParam("token") String token) {
		this.cartService.updateCartItem(cartItemId, addToCartDTO, token);
		ResponseDTO responseDTO = new ResponseDTO(true, "Item updated successfully");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	@PostMapping("/applyCoupon")
    public ResponseEntity<CartDTO> applyCoupon(@RequestParam("token") String token, @RequestBody Coupon coupon){
        CartDTO cartDTO= this.cartService.applyCoupon(coupon,token);
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }
}
