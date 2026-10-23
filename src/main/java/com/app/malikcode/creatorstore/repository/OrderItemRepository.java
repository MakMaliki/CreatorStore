package com.app.malikcode.creatorstore.repository;

import com.app.malikcode.creatorstore.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{ // Spring Data JPA: gives CRUD + pagination methods for OrderItem (keyed by Long id) with no implementation needed
}
