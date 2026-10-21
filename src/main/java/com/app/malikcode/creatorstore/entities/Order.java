package com.app.malikcode.creatorstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity // Marks this class as a JPA entity (maps to a database table)
@Table(name = "orders") // Explicitly names the table "orders"
@Getter // Lombok: generates getters for all fields
@Setter // Lombok: generates setters for all fields
@AllArgsConstructor // Lombok: generates a constructor with all fields as params
@NoArgsConstructor // Lombok: generates a no-args constructor (required by JPA)
public class Order {
    @Id // Marks this field as the table's primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lets the database auto-increment the id
    private Long id;

    @Column(name = "customer_name",nullable = false) // Maps to column "customer_name"; NOT NULL
    private String customerName;

    @Column(name = "customer_email",nullable = false) // Maps to column "customer_email"; NOT NULL
    private String customerEmail;

    @Column(nullable = false) // No name given, so column defaults to field name "status"; NOT NULL
    private String status;

    @JsonManagedReference // Serializes this side of the Order<->OrderItem relationship normally (pairs with @JsonBackReference on OrderItem.order to avoid infinite recursion)
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL) // One Order has many OrderItems; "order" field on OrderItem owns the relationship; ALL cascades save/update/delete from Order down to its items
    private List<OrderItem> orderItems;

    @Column(name = "total_price", nullable = false) // Maps to column "total_price"; NOT NULL
    private BigDecimal totalPrice;

    @Column(name = "created_at", nullable = false) // Maps to column "created_at"; NOT NULL
    private LocalDateTime createdAT;

    //Save Record before save it to Db
    @PrePersist //  runs this method automatically right before the entity is first saved
    public void prePersist(){
        this.createdAT=LocalDateTime.now();
    }


}
