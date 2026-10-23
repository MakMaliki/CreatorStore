package com.app.malikcode.creatorstore.repository;

import com.app.malikcode.creatorstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> { // Spring Data JPA: gives CRUD + pagination methods for Order (keyed by Long id) with no implementation needed
}
