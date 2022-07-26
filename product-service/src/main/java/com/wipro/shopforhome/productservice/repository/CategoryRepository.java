package com.wipro.shopforhome.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.shopforhome.productservice.model.Category;
import org.springframework.stereotype.Repository;

/*
 * Category Repository to interact with the database 
 * and to perform CRUD operations on Category.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Category findByCategoryName(String categoryName);;
}
