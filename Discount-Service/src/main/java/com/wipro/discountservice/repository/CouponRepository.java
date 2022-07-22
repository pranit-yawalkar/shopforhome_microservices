package com.wipro.discountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.discountservice.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Coupon findByCode(String code);
}
