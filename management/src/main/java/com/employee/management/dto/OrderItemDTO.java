package com.employee.management.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class OrderItemDTO {

    private Integer id;
    private Integer productid;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    
}
