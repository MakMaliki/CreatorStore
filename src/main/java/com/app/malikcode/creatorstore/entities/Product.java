package com.app.malikcode.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity // Marks this class as a JPA entity (maps to a database table)
@Table(name = "products") // Explicitly names the table "products"
@Getter // Lombok: generates getters for all fields
@Setter // Lombok: generates setters for all fields
@AllArgsConstructor // Lombok: generates a constructor with all fields as params (needed alongside @Builder)
@NoArgsConstructor // Lombok: generates a no-args constructor (required by JPA)
@Builder // Lombok: generates a builder (Product.builder()...build())
public class Product {
    @Id // Marks this field as the table's primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lets the database auto-increment the id
    private Long id;

    @NotBlank(message = "Product name is Required") // Bean validation: rejects null/empty/whitespace-only values
    @Column(nullable = false) // No name given, so column defaults to field name "name"; NOT NULL
    private String name;

    private String description; // No annotations: nullable, column name defaults to "description"

    private String category; // No annotations: nullable, column name defaults to "category"

    @NotNull(message = "Price is Required") // Bean validation: rejects null (BigDecimal has no "blank" state)
    @Column(nullable = false) // No name given, so column defaults to field name "price"; NOT NULL
    @DecimalMin(value = "0.0", inclusive = false,message = "Price must be greater than Zero") // Must be > 0
    private BigDecimal price;

    @NotNull(message = "Stock Quantity  is Required") // Bean validation: rejects null
    @Min(value=0, message = "stock can not be less than Zero") // Bean validation: must be >= 0
    @Column(name = "stock_quantity", nullable = false) // Maps to column "stock_quantity"; NOT NULL in DB
    private Integer stockQuantity;


    @JsonIgnore //onLY GET ORDER not details of connected tables with it
    @OneToMany(mappedBy = "product") // One Product has many OrderItems; "product" field on OrderItem owns the relationship
    private List<OrderItem> orderItems;
}
