package com.wipro.shopforhome.orderservice.repository;

import com.wipro.shopforhome.orderservice.model.AuthenticationToken;
import com.wipro.shopforhome.orderservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * Token Repository to interact with the database 
 * and to perform CRUD operations on Token.
 */
@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}
