package com.employee.management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.CartItemRequest;
import com.employee.management.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // generates a constructor with required arguments(final fields) for dependency injection 

@RequestMapping("/cart")

public class CartController {

    private final CartService cartService;  // dependency injection 


    @PostMapping
    
    public ResponseEntity<String> addToCart(
        @RequestHeader("X-Student-ID") Integer studentId, // Identifies which student is storing products in the cart, all cart items are asscoiated with a student_id.
        @RequestBody CartItemRequest cartItemRequest){
            if(cartService.addToCart(studentId,cartItemRequest)){
                return ResponseEntity.status(HttpStatus.CREATED).build();

            }
            return ResponseEntity.badRequest().body("Product is out of stock or student not found");
        }
      

    
}
