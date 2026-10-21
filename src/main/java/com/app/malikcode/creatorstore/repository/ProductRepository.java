package com.app.malikcode.creatorstore.repository;

import com.app.malikcode.creatorstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> { // Spring Data JPA: gives CRUD + pagination methods for Product (keyed by Long id) with no implementation needed

}
