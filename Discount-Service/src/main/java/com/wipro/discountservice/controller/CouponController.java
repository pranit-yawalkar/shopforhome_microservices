package com.wipro.discountservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.discountservice.model.Coupon;
import com.wipro.discountservice.repository.CouponRepository;
import com.wipro.discountservice.response.ApiResponse;
import com.wipro.discountservice.service.CouponService;

/*
 * Coupon Controller to handle all the requests coming from the 
 * client side and to provide a required response.
 * 
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController {
	@Autowired
	private CouponService couponService;

	@Autowired
	private CouponRepository couponRepository;

	@PostMapping("/create")
	public ResponseEntity<Coupon> createCoupon(@RequestParam("role") String role, @RequestBody Coupon coupon)
			throws Exception {
		Coupon couponCreated = this.couponService.createCoupon(role, coupon);
		return new ResponseEntity<>(couponCreated, HttpStatus.CREATED);
	}

	@PostMapping("/createAll")
	public ResponseEntity<List<Coupon>> createCoupon(@RequestBody List<Coupon> coupons) {
		List<Coupon> couponList = new ArrayList<>();
		coupons.forEach(c -> {
			Coupon createdCoupon = this.couponRepository.save(c);
			couponList.add(createdCoupon);
		});
		return new ResponseEntity<>(couponList, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Coupon>> getAllCoupons() {
		List<Coupon> coupons = this.couponService.getAllCoupons();
		return new ResponseEntity<>(coupons, HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Coupon> editCoupon(@PathVariable Long id, @RequestParam("role") String role,
			@RequestBody Coupon coupon) throws Exception {
		Coupon responseCoupon = this.couponService.editCoupoun(id, coupon);
		if (responseCoupon == null) {
			return new ResponseEntity<>(responseCoupon, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(responseCoupon, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCoupon(@PathVariable Long id, @RequestParam("role") String role)
			throws Exception {
		this.couponService.deleteCoupon(id, role);
		ApiResponse apiResponse = new ApiResponse(true, "coupon deleted successfully");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/getCoupon/{code}")
	public ResponseEntity<Coupon> getCouponByCode(@PathVariable("code") String code) throws Exception {
		Coupon coupon = this.couponService.getCouponByCode(code);
		return new ResponseEntity<>(coupon, HttpStatus.OK);
	}

	@GetMapping("/get/{couponId}")
	public ResponseEntity<Coupon> getCouponById(@PathVariable Long couponId, @RequestParam("role") String role)
			throws Exception {
		Coupon coupon = this.couponService.getCouponById(couponId, role);
		return new ResponseEntity<>(coupon, HttpStatus.OK);
	}

}
