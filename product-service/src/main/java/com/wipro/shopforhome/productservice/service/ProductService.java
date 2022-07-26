package com.wipro.shopforhome.productservice.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.shopforhome.productservice.dto.ProductDTO;
import com.wipro.shopforhome.productservice.exception.ResourceNotFoundException;
import com.wipro.shopforhome.productservice.model.Category;
import com.wipro.shopforhome.productservice.model.Product;
import com.wipro.shopforhome.productservice.repository.ProductRepository;

/*
 * Product Service class to define the business logic and interact with the 
 * Product Repository.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getProduct(ProductDTO productDTO) {
    	Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setImageUrl(productDTO.getImageUrl());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(this.categoryService.getCategoryById(productDTO.getCategoryId()));
        return product;
    }
    
    public Product createProduct(ProductDTO productDTO) {
        Product product = getProduct(productDTO);
        return this.productRepository.save(product);
    }
    
    public List<Product> addAllProducts(List<ProductDTO> productDTOs) {
    	List<Product> products = new ArrayList<>();
    	productDTOs.forEach(productDTO -> {
    		Product product = getProduct(productDTO);
    		products.add(product);
    	});
    	
    	return this.productRepository.saveAll(products);
    }

    public ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setCategoryId(product.getCategory().getId());
        return productDTO;
    }

    public Product getProductById(Long id) {
       return this.productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product does not exist"));
    }
    
    public List<ProductDTO> getProductByCategory(String categoryName) {
    	Category category = this.categoryService.getCategoryByName(categoryName);
    	List<Product> products = this.productRepository.findByCategory(category);
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(getProductDTO(product));
        });
        return productDTOS;
    }
    
    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
    	Category category = this.categoryService.getCategoryById(categoryId);
    	List<Product> products = this.productRepository.findByCategory(category);
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(getProductDTO(product));
        });
        return productDTOS;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = this.productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(getProductDTO(product));
        });
        return productDTOS;
    }

    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product does not exist"));
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        product.setDescription(productDTO.getDescription());
        product.setCategory(this.categoryService.getCategoryById(productDTO.getCategoryId()));
        return this.productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
    	Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product does not exist"));
        this.productRepository.delete(product);
    }
}
