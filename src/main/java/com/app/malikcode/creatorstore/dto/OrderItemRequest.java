package com.app.malikcode.creatorstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter // Lombok: generates getters for all fields
@Setter // Lombok: generates setters for all fields
@NoArgsConstructor // Lombok: generates a no-args constructor
@AllArgsConstructor // Lombok: generates a constructor with all fields as params (needed alongside @Builder)
@Builder // Lombok: generates a builder (OrderItemRequest.builder()...build())
public class OrderItemRequest {
    @NotNull(message = "Product ID is required") // Bean validation: rejects null
    private Long productId;

    @NotNull(message = "Quantity is required") // Bean validation: rejects null
    @Min(value = 1 ,message = "Quantity must be at least  1") // Bean validation: must be >= 1
    private Integer quantity;
}
