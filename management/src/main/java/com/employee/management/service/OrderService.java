package com.employee.management.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.management.dto.OrderResponse;
import com.employee.management.model.CartItem;
import com.employee.management.model.Order;
import com.employee.management.model.OrderItem;
import com.employee.management.model.OrderStatus;
import com.employee.management.model.Student;
import com.employee.management.repository.StudentRepository;
import com.employee.management.repository.CartItemRepository;
import com.employee.management.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;
    private final CartItemRepository cardItemRepository;

    public Optional<OrderResponse> createOrder(Integer studentid){

        //Validate the Cart
        List<CartItem> cartItems = cartService.getCart(studentid);

        if(cartItems.isEmpty()) return Optional.empty();




        //Validate the User
        Optional<Student> studentOptional = studentRepository.findById(studentid);
        if(studentOptional.isEmpty()) return Optional.empty();
        Student student = studentOptional.get();
        //Calculate totalPrice
        BigDecimal totalPrice = cartItems.stream()
        .map(CartItem::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);  // .reduce(if,else) -> if not found gives zero, found is adding the price
        //reduce - into one single value
        //BigDecimal.Zero if cartItems is empty returns 0
        //::add combines values.


        //Create Order

        Order order = new Order();
        order.setStudent(student);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
        .map(item -> new OrderItem(
            null,
            item.getProduct(),
            item.getQuantity(),
            item.getPrice(),
            order
        ))
        .toList();
        order.setItems(orderItems);
        Order savOrder = orderRepository.save(order);

        //Clear the cart
    

        

    }
    
}
