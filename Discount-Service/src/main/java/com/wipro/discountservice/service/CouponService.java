package com.wipro.discountservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.discountservice.exceptions.CustomException;
import com.wipro.discountservice.exceptions.ResourceNotFoundException;
import com.wipro.discountservice.model.Coupon;
import com.wipro.discountservice.repository.CouponRepository;

/*
 * Coupon Service class to define the business logic and interact with the 
 * Coupon Repository.
 * 
 */
@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	public Coupon createCoupon(String role, Coupon coupon) throws Exception {
		if (role.equals("admin")) {
			return this.couponRepository.save(coupon);
		} else {
			throw new CustomException("Access Denied!");
		}
	}

	public Coupon editCoupoun(Long id, Coupon coupon) throws ResourceNotFoundException {
		Coupon response = this.couponRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("coupon not found"));
		response.setCode(coupon.getCode());
		response.setPercentage(coupon.getPercentage());
		this.couponRepository.save(response);
		return response;
	}

	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = couponRepository.findAll();
		return coupons;
	}

	public void deleteCoupon(Long id, String role) throws Exception {
		if (role.equals("admin")) {
			Coupon responseCoupon = this.couponRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("coupon not found"));
			this.couponRepository.delete(responseCoupon);
		} else {
			throw new CustomException("Access Denied!");
		}
	}

	public Coupon getCouponByCode(String code) throws Exception {
		Coupon coupon = this.couponRepository.findByCode(code);
		if (coupon == null) {
			throw new ResourceNotFoundException("coupon not found");
		}
		return coupon;
	}

	public Coupon getCouponById(Long id, String role) throws Exception {
		if (role.equals("admin")) {
			Coupon response = this.couponRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("coupon not found"));
			return response;
		} else {
			throw new CustomException("Access Denied!");
		}

	}

}
