package com.employee.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.OrderResponse;
import com.employee.management.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping

    public ResponseEntity<OrderResponse> createOrder(@RequestHeader("X-Student-ID") Integer student_id){
        return orderService.createOrder(student_id)
        .map(orderResponse->new ResponseEntity<>(orderResponse,HttpStatus))
        .orElseGet(()->ResponseEntity.badRequest().build());
    }
    
}
