package com.wipro.shopforhome.orderservice.repository;


import com.wipro.shopforhome.orderservice.model.User;
import com.wipro.shopforhome.orderservice.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> getWishListByUser(User user);
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}