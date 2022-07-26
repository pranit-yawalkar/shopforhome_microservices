package com.wipro.shopforhome.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.shopforhome.orderservice.model.Order;
import com.wipro.shopforhome.orderservice.model.User;

/*
 * Order Repository to interact with the database 
 * and to perform CRUD operations on Order.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUser(User user);

	List<Order> findAllByOrderByCreatedDateDesc();

	List<Order> findAllByOrderByCreatedDateAsc();

}