package com.wipro.shopforhome.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
