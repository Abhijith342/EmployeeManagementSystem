package com.employee.management.dto;

import java.math.BigDecimal;

import com.employee.management.model.Product;
import com.employee.management.model.Student;

import lombok.Data;
@Data

public class CartItemResponse {
    private Student student;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
    
}
