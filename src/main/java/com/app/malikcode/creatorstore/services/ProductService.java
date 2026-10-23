package com.app.malikcode.creatorstore.services;

import com.app.malikcode.creatorstore.entities.Product;
import com.app.malikcode.creatorstore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service // Marks this as a Spring-managed service bean, eligible for injection (e.g. into ProductController)
@RequiredArgsConstructor // Lombok: generates a constructor for all "final" fields, enabling constructor injection
public class ProductService {
    //Service Layer is responsible for Business logic
    //and also talking to Database IF Neccessory

    //with Database I can Connect to Database
    //which has some built in database
    //when we Inject it here we can access those methods
    private final ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct( Long id,Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productRepository.save(existingProduct) ;
    }

    public List<Product> getProducts(){
        return  productRepository.findAll();
    }

    public Product getProductById( Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));
    }

    public void deleteProduct(Long id){
    productRepository.deleteById(id);
    }
}
