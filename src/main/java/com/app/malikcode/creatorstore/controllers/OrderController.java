package com.app.malikcode.creatorstore.controllers;

import com.app.malikcode.creatorstore.dto.OrderRequest;
import com.app.malikcode.creatorstore.entities.Order;
import com.app.malikcode.creatorstore.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody OrderRequest orderRequest){
     return orderService.createOrder(orderRequest);
    };


    //TODO: TO BE IMPLEMENTED
    //Get all Orders
    @GetMapping() // Handles GET /api/orders
    public List<Order> getAllOrders(){
        return  orderService.getAllOrders();
    }

    //Get order by id

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable  Long id){
        return  orderService.getAllOrderById(id);
    }

}
