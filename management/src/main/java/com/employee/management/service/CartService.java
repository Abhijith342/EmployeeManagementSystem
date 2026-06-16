package com.employee.management.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.management.dto.CartItemRequest;
import com.employee.management.model.CartItem;
import com.employee.management.model.Product;
import com.employee.management.model.Student;
import com.employee.management.repository.CartItemRepository;
import com.employee.management.repository.ProductRepository;
import com.employee.management.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CartService {
    private final ProductRepository productRepository;
    private final StudentRepository studentRepository;
    private final CartItemRepository cartItemRepository;

    public boolean addToCart(Integer studentId,CartItemRequest cartItemRequest){
        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProduct().getId());
        if(productOpt.isEmpty()){
            return false;
        }

        Product product = productOpt.get();
        if(product.getStockQuantity() < cartItemRequest.getQuantity()){
            return false;
        }
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if(studentOpt.isEmpty()){
            return false;
        }
        Student student = studentOpt.get();
        CartItem existingCartItem = cartItemRepository.findByStudentAndProduct(student,product);

        if(existingCartItem == null){
            // create a new cartItem
            CartItem newCartItem = new CartItem();
            newCartItem.setStudent(student);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(newCartItem.getQuantity())));
            cartItemRepository.save(newCartItem);
            
        }
        else{
            // Update the cartItem
            existingCartItem.setQuantity(existingCartItem.getQuantity()+cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }
        return true;



    }
    @Transactional
    public boolean deleteItemFromCart(Integer studentId,Integer productId){
        Optional<Product> productopt = productRepository.findById(productId);
        Optional<Student> studentopt = studentRepository.findById(studentId);

        if(studentopt.isPresent() && productopt.isPresent()){
            cartItemRepository.deleteByStudentIdAndProductId(studentopt.get().getId(),productopt.get().getId());
            return true;
        }
        return false;
    }
    public List<CartItem> getCart(Integer studentId){
        return studentRepository.findById(studentId).map(cartItemRepository::findByStudent).
        orElseGet(List::of);
    }

    @Transactional

    public void clearCart(Integer studentId){
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if(studentOpt.isEmpty()) return;
        Student student = studentOpt.get();
        cartItemRepository.deleteByStudentId(student.getId());
    }

    
}
