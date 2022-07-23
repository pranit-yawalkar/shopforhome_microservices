package com.wipro.shopforhome.userservice.repository;

import com.wipro.shopforhome.userservice.model.AuthenticationToken;
import com.wipro.shopforhome.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Long> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
    
    void deleteByUser(User user);
}