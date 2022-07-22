package com.wipro.discountservice.controller;

import com.wipro.discountservice.response.ApiResponse;
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
import org.springframework.web.bind.annotation.RestController;
import com.wipro.discountservice.exceptions.ResourceNotFoundException;
import com.wipro.discountservice.model.Coupon;
import com.wipro.discountservice.repository.CouponRepository;
import com.wipro.discountservice.service.CouponService;
import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	@PostMapping("/create")
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
		this.couponService.createCoupon(coupon);
		return new ResponseEntity<>(coupon,HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Coupon>> getAllCoupons(){
		List<Coupon> coupons= this.couponService.getAllCoupons();
		return new ResponseEntity<List<Coupon>>(coupons,HttpStatus.OK);
		
	}

		
	@PutMapping("/edit/{id}")
	public ResponseEntity<Coupon> editCoupon(@PathVariable Integer id, @RequestBody Coupon coupon) throws Exception{
			Coupon responseCoupon = this.couponService.editCoupoun(id, coupon);
			if(responseCoupon==null) {
				return new ResponseEntity<>(responseCoupon, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(responseCoupon,HttpStatus.OK);
		
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCoupon(@PathVariable Integer id) throws Exception{
		this.couponService.deleteCoupon(id);
		ApiResponse apiResponse= new ApiResponse(true,"coupon deleted successfully");
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}

	@GetMapping("/getCoupon/{code}")
	public ResponseEntity<Coupon> getCouponByCode(@PathVariable("code") String code) throws Exception{
			Coupon coupon = this.couponService.getCouponByCode(code);
		return new ResponseEntity<>(coupon,HttpStatus.OK);
	}

}
