package com.wipro.discountservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Coupon Model Class in which attributes 
 * and methods related to Coupon model are defined.
 * 
 */
@Entity
@Table(name = "Coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "PERCENTAGE", nullable = false)
	private double percentage;

	public Coupon(Long id, String code, double percentage) {
		super();
		this.id = id;
		this.code = code;
		this.percentage = percentage;
	}

	public Coupon() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

}