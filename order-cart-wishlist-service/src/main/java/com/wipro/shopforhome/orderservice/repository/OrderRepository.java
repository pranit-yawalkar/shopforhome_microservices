package com.wipro.shopforhome.orderservice.repository;


import com.wipro.shopforhome.orderservice.model.Order;
import com.wipro.shopforhome.orderservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}