package com.wipro.shopforhome.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.shopforhome.productservice.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Category findByCategoryName(String categoryName);;
}
