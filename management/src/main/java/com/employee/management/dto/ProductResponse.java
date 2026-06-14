package com.employee.management.dto;

import java.math.BigDecimal;

import lombok.Data;
@Data

public class ProductResponse {
    
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String imageurl;
    private String category;
    private Boolean active;
}
