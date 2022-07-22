package com.wipro.shopforhome.orderservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordered_item_id")
	private Long id;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderItem(int quantity, Double price, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
}
