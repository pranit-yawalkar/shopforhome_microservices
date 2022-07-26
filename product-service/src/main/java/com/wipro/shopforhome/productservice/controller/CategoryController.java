package com.wipro.shopforhome.productservice.controller;

import com.wipro.shopforhome.productservice.dto.ResponseDTO;
import com.wipro.shopforhome.productservice.model.Category;
import com.wipro.shopforhome.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * Category Controller to handle all the requests coming from the 
 * client side and to provide a required response.
 * 
 */
@RestController
@RequestMapping("/api/category/")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = this.categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
		Category category = this.categoryService.getCategoryById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@GetMapping("/get/category/{categoryName}")
	public ResponseEntity<Category> getCategoryById(@PathVariable String categoryName) {
		Category category = this.categoryService.getCategoryByName(categoryName);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		Category responseCategory = this.categoryService.createCategory(category);
		return new ResponseEntity<>(responseCategory, HttpStatus.CREATED);
	}

	@PutMapping("/update/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		Category newCategory = this.categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<>(newCategory, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
		ResponseDTO responseDTO = new ResponseDTO("true", "Category Deleted Successfully!");
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

}
