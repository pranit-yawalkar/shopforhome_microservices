package com.wipro.shopforhome.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.shopforhome.productservice.dto.ProductDTO;
import com.wipro.shopforhome.productservice.model.Product;
import com.wipro.shopforhome.productservice.service.ProductService;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        Product response = productService.createProduct(productDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOS = this.productService.getAllProducts();
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {

        Product product = this.productService.getProductById(productId);
        ProductDTO productDTO = this.productService.getProductDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        Product product = this.productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    
    @GetMapping("/get/category/{category}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable String category) {
    	List<ProductDTO> productDTOs = this.productService.getProductByCategory(category);
    	return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }
    
    @GetMapping("/get/categoryid/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductByCategoryId(@PathVariable Long categoryId) {
    	List<ProductDTO> productDTOs = this.productService.getProductByCategoryId(categoryId);
    	return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }
}
