package com.app.malikcode.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity // Marks this class as a JPA entity (maps to a database table)
@Table(name = "order_items") // Explicitly names the table "order_items"
@Getter // Lombok: generates getters for all fields
@Setter // Lombok: generates setters for all fields
@Builder // Lombok: generates a builder (OrderItem.builder()...build())
@NoArgsConstructor // Lombok: generates a no-args constructor (required by JPA)
@AllArgsConstructor // Lombok: generates a constructor with all fields as params (needed alongside @Builder)
public class OrderItem {
    @Id // Marks this field as the table's primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lets the database auto-increment the id
    private Long id;

    @Column(nullable = false) // No name given, so column defaults to field name "quantity"; NOT NULL
    private Integer quantity;

    @Column(name = "price_at_purchase", nullable = false) // Maps to column "price_at_purchase"; NOT NULL
    private BigDecimal priceAtPurchase;
    //TODO: Relations

    @JsonBackReference // Omits this side of the Order<->OrderItem relationship during serialization (pairs with @JsonManagedReference on Order.orderItems to avoid infinite recursion)
    @ManyToOne //  many OrderItems can point to the same Order (the "many" side of the relationship)
    @JoinColumn(name = "order_id",nullable = false) // : FK column "order_id" in this table; NOT NULL
    private Order order;

    @ManyToOne // Many OrderItems can point to the same Product (the "many" side of the relationship); no mappedBy, so this is the owning side and "product_id" is managed here
    @JoinColumn(name = "product_id", nullable = false) // FK column "product_id" in this table; NOT NULL
    private Product product;

}
