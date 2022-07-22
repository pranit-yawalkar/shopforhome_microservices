package com.wipro.discountservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.discountservice.exceptions.ResourceNotFoundException;
import com.wipro.discountservice.model.Coupon;
import com.wipro.discountservice.repository.CouponRepository;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;
	
	public void createCoupon(Coupon coupon) {
		couponRepository.save(coupon);
	}

	public Coupon editCoupoun(Integer id, Coupon coupon) throws ResourceNotFoundException{
		Coupon response= this.couponRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("coupon not found"));
		response.setCode(coupon.getCode());
		response.setPercentage(coupon.getPercentage());
		this.couponRepository.save(response);
		return response;	
	}

	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons=couponRepository.findAll();
		return coupons;
		// TODO Auto-generated method stub
		
	}

	public String deleteCoupon(Integer id) throws Exception{
		Coupon responseCoupon= this.couponRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("coupon not found"));
		this.couponRepository.delete(responseCoupon);
		return "deleted Successfully";
	}

	public Coupon getCouponByCode(String code) throws Exception{
		Coupon coupon=this.couponRepository.findByCode(code);
		if(coupon==null){
			throw new ResourceNotFoundException("coupon not found");
		}
		return coupon;
	}
}
