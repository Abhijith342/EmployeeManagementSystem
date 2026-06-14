package com.employee.management.service;

import com.employee.management.dto.ProductResponse;
import com.employee.management.model.Product;
import com.employee.management.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.management.dto.ProductRequest;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    public void updateProductFromRequest(Product product, ProductRequest productRequest){
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageurl(productRequest.getImageurl());
        product.setCategory(productRequest.getCategory());
        
        
    }

    private ProductResponse mapToProductResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setDescription(product.getDescription());
        response.setStockQuantity(product.getStockQuantity());
        response.setImageurl(product.getImageurl());
        response.setCategory(product.getCategory());
        response.setActive(product.getActive());
        return response;

    }

    public ProductResponse addProduct(ProductRequest productRequest){
        Product product = new Product();
        updateProductFromRequest(product,productRequest);
        Product saveProduct = productRepository.save(product);
        return mapToProductResponse(saveProduct);

    }

    public List<ProductResponse> getAllProducts(){
        return productRepository.findAll()
        .stream().map(this::mapToProductResponse)
        .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Integer id){
        // return productRepository.findById(id)
        // .map(this::mapToProductResponse).orElse(null);

        Product product = productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Product id not found"));
        return mapToProductResponse(product);
    }

    public Optional<ProductResponse> updateById(Integer id,ProductRequest productRequest){
        return productRepository.findById(id).map(existingproductRequest->{
            updateProductFromRequest(existingproductRequest, productRequest);
            Product savedProduct = productRepository.save(existingproductRequest);
            return mapToProductResponse(savedProduct);
        });
    }

    public String deleteProduct(Integer id){
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product id not found"));
        product.setActive(false);
        return "Product deactivated";
    }

    public List<ProductResponse> searchProduct(String keyword){
        //Method 1 : using query anotation

        return productRepository.searchProduct(keyword)
        .stream().map(this::mapToProductResponse).collect(Collectors.toList());
        

        //Method 2: using in built-function
    }
    
}
