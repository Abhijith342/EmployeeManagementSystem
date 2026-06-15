package com.employee.management.dto;

import java.math.BigDecimal;

import com.employee.management.model.Product;
import com.employee.management.model.Student;

import lombok.Data;

@Data
public class OrderRequest {
    private Student student;
    private Product product;
    private Integer quantity;
    private BigDecimal totalAmount;


    
}
