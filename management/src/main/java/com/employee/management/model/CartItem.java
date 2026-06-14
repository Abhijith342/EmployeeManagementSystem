package com.employee.management.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "Cartitem")

// CartItem - Products added to Cart

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @ManyToOne  // many cart items can belong to one student, ex: one student selects adds lot of products to the cart.
    @JoinColumn(name = "student_id",nullable = false) // nullable = false means that every cart item must be associated with a student. student_id cannot be null.
    private Student student;
    @ManyToOne // many cart items can belong to one product, ex: student A and Student B orders same product
    @JoinColumn(name="product_id",nullable = false)
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
}
