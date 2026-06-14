package com.employee.management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.employee.management.service.ProductService;
import com.employee.management.dto.ProductRequest;
import com.employee.management.dto.ProductResponse;
// import com.employee.management.model.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor    // generates a constructor with required arguments(final fields) for dependency injection 
@RequestMapping("/pro")

public class ProductController {
    private final ProductService productService;   // dependency injection 
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product){

        return new ResponseEntity<ProductResponse>(productService.addProduct(product),HttpStatus.CREATED);
    }

    @GetMapping
    
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")

    public ResponseEntity<ProductResponse> getProductbyId(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")

    public ResponseEntity<ProductResponse> updateById(@PathVariable Integer id,  @RequestBody ProductRequest productRequest){
        return productService.updateById(id,productRequest)
        .map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        String res = productService.deleteProduct(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")

    public ResponseEntity<List<ProductResponse>> searchProduct(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));
    }





    
}
