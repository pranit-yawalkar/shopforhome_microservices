package com.wipro.shopforhome.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wipro.shopforhome.orderservice.dto.ProductDTO;
import com.wipro.shopforhome.orderservice.dto.wishlist.AddToWishListDTO;
import com.wipro.shopforhome.orderservice.dto.wishlist.WishListDTO;
import com.wipro.shopforhome.orderservice.dto.wishlist.WishListItemDTO;
import com.wipro.shopforhome.orderservice.exception.CustomException;
import com.wipro.shopforhome.orderservice.exception.ResourceNotFoundException;
import com.wipro.shopforhome.orderservice.model.Category;
import com.wipro.shopforhome.orderservice.model.Product;
import com.wipro.shopforhome.orderservice.model.User;
import com.wipro.shopforhome.orderservice.model.WishList;
import com.wipro.shopforhome.orderservice.repository.WishListRepository;

/*
 * Wishlist Service class to define the business logic and interact with the 
 * Wishlist Repository, Cart Service, Product Service and Authentication Service.
 */
@Service
public class WishListService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WishListRepository wishListRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	private AuthenticationService authenticationService;

	public WishList createWishList(ProductDTO productDTO, String token) {
		// authenticate the token
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);

		// check if product is already present in the wishlist of the same user
		List<WishList> wishLists = getWishListByUser(user);

		wishLists.forEach(wishList -> {
			if (wishList.getProduct().getId().equals(productDTO.getProductId())) {
				throw new CustomException("Product already added to the wishlist");
			}
		});

		Category category = this.restTemplate
				.getForObject("http://product-service/api/category/get/" + productDTO.getCategoryId(), Category.class);

		Product product = this.cartService.getProductByProductDTO(productDTO);

		// save the item in wishlist
		WishList wishList = new WishList(user, product);

		return wishListRepository.save(wishList);
	}

	public WishListItemDTO createWishList(AddToWishListDTO addToWishListDTO, String token) {
		// authenticate the token
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);

		// check if product is already present in the wishlist of the same user
		List<WishList> wishLists = getWishListByUser(user);

		wishLists.forEach(wishList -> {
			if (wishList.getProduct().getId().equals(addToWishListDTO.getProductId())) {
				throw new CustomException("Product already added to the wishlist");
			}
		});

		// save the item in wishlist
		ProductDTO productDTO = this.restTemplate.getForObject(
				"http://product-service/api/product/get/" + addToWishListDTO.getProductId(), ProductDTO.class);

		Category category = null;
		Product product = null;
		if (productDTO != null) {
			category = this.restTemplate.getForObject(
					"http://product-service/api/category/get/" + productDTO.getCategoryId(), Category.class);
			product = this.cartService.getProductByProductDTO(productDTO);
		}

		WishList wishList = new WishList(user, product);
		wishList = this.wishListRepository.save(wishList);
		return new WishListItemDTO(wishList.getId(), productDTO);
	}

	public List<WishList> getAllWishList() {
		return this.wishListRepository.findAll();
	}

	public List<WishList> getWishListByUser(User user) {
		return this.wishListRepository.getWishListByUser(user);
	}

	public List<WishListDTO> getWishListByUserOrderByCreatedDateDesc(String token) {
		// authenticate the token
		authenticationService.authenticate(token);

		// find the user
		User user = authenticationService.getUser(token);
		List<WishList> wishLists = this.wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<WishListDTO> wishListDTOS = new ArrayList<>();

		wishLists.forEach(wishList -> {
			ProductDTO productDTO = this.restTemplate.getForObject(
					"http://product-service/api/product/get/" + wishList.getProduct().getId(), ProductDTO.class);
			WishListDTO wishListDTO = new WishListDTO(wishList.getId(), productDTO);
			wishListDTOS.add(wishListDTO);
		});

		return wishListDTOS;
	}

	public void deleteWishListItem(Long itemId, String token) {
		this.authenticationService.authenticate(token);

		User user = this.authenticationService.getUser(token);

		WishList wishList = this.wishListRepository.findById(itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item id is not valid"));

		if (wishList.getUser() != user) {
			throw new CustomException("WishList does not belong to the user");
		}

		this.wishListRepository.delete(wishList);
	}
}
