package com.wipro.shopforhome.userservice.repository;

import com.wipro.shopforhome.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * User Repository to interact with the database 
 * and to perform CRUD operations on User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}