package com.wipro.shopforhome.orderservice.repository;


import com.wipro.shopforhome.orderservice.model.Order;
import com.wipro.shopforhome.orderservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    
//    @Query("from orders t where date(t.created_date)=:createdDate")
    List<Order> findAllByOrderByCreatedDateDesc();
    
//    @Query("select distinct date(created_date) from orders")
//    List<Date> findDistinctCreatedDate();
}