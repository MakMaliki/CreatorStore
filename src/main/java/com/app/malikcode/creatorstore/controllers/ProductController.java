package com.app.malikcode.creatorstore.controllers;


import com.app.malikcode.creatorstore.entities.Product;
import com.app.malikcode.creatorstore.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//These are our REST Endpoints  which allows clients to communicate to our Backend

@RestController // Marks this class as a REST controller; return values are serialized straight to the response body (no view resolution)
@RequestMapping("/api/products") // Base path for every endpoint in this class
@RequiredArgsConstructor // Lombok: generates a constructor for all "final" fields, enabling constructor injection
public class ProductController {

    //Controller uses single Responsible Principle


    //Controller Has single Responsiblity
    //Mapping Endpoints and hands of its tasks to SERVICE LAYER


    //Service Layer is respoponsible for Bussiness logic
    //and also talking to Db IF NECESSORY

    private final ProductService productService;
    @PostMapping // Handles POST /api/products
    public Product createProduct(
            @Valid // Triggers Bean Validation on the deserialized object (the @NotBlank/@NotNull/etc. on Product); invalid requests short-circuit with a 400 before the method body runs
            @RequestBody // Deserializes the raw JSON request body into a Product
            Product product){
        return productService.createProduct(product);

    }

    @PutMapping("/{id}") // Handles PUT /api/products/{id}
    public Product updateProduct(
            @PathVariable // Binds the "{id}" path segment to this parameter
            Long id,
            @Valid @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @GetMapping() // Handles GET /api/products
    public List<Product> getProducts(){
        return  productService.getProducts();
    }

    @GetMapping("/{id}") // Handles GET /api/products/{id}
    public Product getProductById(
            @PathVariable // Binds the "{id}" path segment to this parameter
            Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}") // Handles DELETE /api/products/{id}
    public void deleteProduct( @PathVariable Long id){
        productService.deleteProduct(id);
    }

}
