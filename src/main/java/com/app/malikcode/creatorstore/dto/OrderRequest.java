package com.app.malikcode.creatorstore.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter // Lombok: generates getters for all fields
@Setter // Lombok: generates setters for all fields
@NoArgsConstructor // Lombok: generates a no-args constructor
@AllArgsConstructor // Lombok: generates a constructor with all fields as params (needed alongside @Builder)
@Builder // Lombok: generates a builder (OrderRequest.builder()...build())
public class OrderRequest {

    @NotNull(message = "Customer  Name is required") // Bean validation: rejects a null value (unlike @NotBlank, an empty string "" still passes)
    private String customerName;
    @NotNull(message = "Customer  Email is required") // Bean validation: rejects null
    @Email(message = "Enter a Valid Email") // Bean validation: must match a valid email format
    private String customerEmail;

    @Valid // Cascades Bean Validation into each OrderItemRequest in the list (without this, their @NotNull/@Min annotations would be ignored)
    @NotEmpty(message = "Order must contain at least one Item") // Bean validation: rejects null AND an empty list (@NotNull alone would still allow [])
    private List<OrderItemRequest> items;
}
