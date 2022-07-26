package com.wipro.shopforhome.userservice.repository;

import com.wipro.shopforhome.userservice.model.AuthenticationToken;
import com.wipro.shopforhome.userservice.model.User;
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
    
    void deleteByUser(User user);
}