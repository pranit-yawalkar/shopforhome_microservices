package com.wipro.shopforhome.productservice.repository;

import com.wipro.shopforhome.productservice.model.Category;
import com.wipro.shopforhome.productservice.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByCategory(Category category);	
}
