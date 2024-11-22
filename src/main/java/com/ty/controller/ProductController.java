package com.ty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ty.entity.Product;
import com.ty.responsestructure.ResponseStructure;
import com.ty.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a new product
    @PostMapping("/{categoryId}")
    public ResponseStructure<Product> createProduct(@RequestBody Product product, @PathVariable Integer categoryId) {
        return productService.createProduct(product, categoryId);
    }


    // Get all products with pagination
    @GetMapping
    public ResponseStructure<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> getProductById(@PathVariable Integer id) {
        ResponseStructure<Product> product = productService.getById(id);
        return ResponseEntity.ok(product);
    }


    // Update product by ID
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@PathVariable Integer id, @RequestBody Product productDetails) {
        ResponseStructure<Product> response = productService.updateProduct(id, productDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



 // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseStructure<Void> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

}
