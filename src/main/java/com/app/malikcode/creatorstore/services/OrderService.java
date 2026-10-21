package com.app.malikcode.creatorstore.services;

import com.app.malikcode.creatorstore.dto.OrderItemRequest;
import com.app.malikcode.creatorstore.dto.OrderRequest;
import com.app.malikcode.creatorstore.entities.Order;
import com.app.malikcode.creatorstore.entities.OrderItem;
import com.app.malikcode.creatorstore.entities.Product;
import com.app.malikcode.creatorstore.repository.OrderRepository;
import com.app.malikcode.creatorstore.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    //Making sure its creates new records in Order Tables
    //More than one SQL Statement to Run we group them into one
    //That is why we use Transactional
    @Transactional
    public Order createOrder(OrderRequest orderRequest){

        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("CONFIRMED");

        for(OrderItemRequest itemRequest: orderRequest.getItems()){

            Product product = productRepository.findById(
                    itemRequest.getProductId()
            ).orElseThrow(()-> new RuntimeException("Product not found with ID"+ itemRequest.getProductId()));

            //Check the Product Stock

            if(product.getStockQuantity()<itemRequest.getQuantity()){
              throw new RuntimeException("Not enough stock for this product"+itemRequest.getProductId());
            }

            //Calculate Total Price

            BigDecimal priceOfItem =
                    product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            totalPrice = totalPrice.add(priceOfItem);


            //Update Product Table with latest stock Quanitity

            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());

            productRepository.save(product);

            //Builder Patter to make Object

            OrderItem orderItem = OrderItem.builder().order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .priceAtPurchase(product.getPrice())
                    .build();

            orderItems.add(orderItem);


        };

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);

        return   orderRepository.save(order);
    }


    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    public Order getAllOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id " + id));
    }
}
