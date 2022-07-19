package com.wipro.shopforhome.orderservice.controller;


import com.wipro.shopforhome.orderservice.dto.ResponseDTO;
import com.wipro.shopforhome.orderservice.dto.cart.AddToCartDTO;
import com.wipro.shopforhome.orderservice.dto.cart.CartDTO;
import com.wipro.shopforhome.orderservice.model.Cart;
import com.wipro.shopforhome.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart/")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartDTO addToCartDTO,
                                          @RequestParam("token") String token) {

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
        ResponseDTO ResponseDTO = new ResponseDTO(true, "Item deleted successfully");
        return new ResponseEntity<>(ResponseDTO, HttpStatus.OK);
    }
}
