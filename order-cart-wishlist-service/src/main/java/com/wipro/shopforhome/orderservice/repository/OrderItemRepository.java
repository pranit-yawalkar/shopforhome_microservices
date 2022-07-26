package com.wipro.shopforhome.orderservice.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.shopforhome.orderservice.model.OrderItem;


/*
 * Order Item Repository to interact with the database 
 * and to perform CRUD operations on Order Item.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
}
