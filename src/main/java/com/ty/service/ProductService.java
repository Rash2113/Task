package com.ty.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import com.ty.entity.Category;
import com.ty.entity.Product;
import com.ty.exception.ResourcesNotFoundException;
import com.ty.repository.CategoryRepository;
import com.ty.repository.ProductRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    // Create new product
    public ResponseStructure<Product> createProduct(Product product, Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Category not found with ID: " + id));
        product.setCategory(category);
        Product save = productRepository.save(product);
        return new ResponseStructure<>(HttpStatus.CREATED.value(), "Product created successfully", save);
    }

    // Get all products with pagination
    public ResponseStructure<List<Product>> getAllProducts(int page, int size) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        List<Product> products = productPage.getContent();
        return new ResponseStructure<>(HttpStatus.OK.value(), "Products fetched successfully", products);
    }

    // Get product by ID
    public ResponseStructure<Product> getById(Integer id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Product not found with ID: " + id));
        return new ResponseStructure<>(HttpStatus.OK.value(), "Product found", product);
    }

    // Update product by ID
    public ResponseStructure<Product> updateProduct(Integer id, Product productDetails) {
        // Fetch the existing product
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Product not found with ID: " + id));

        // Update product details
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        // Save the updated product
        Product updatedProduct = productRepository.save(product);

        // Return the response with the updated product
        return new ResponseStructure<>(HttpStatus.OK.value(), "Product updated successfully", updatedProduct);
    }


    // Delete product by ID
    public ResponseStructure<Void> deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Product not found with ID: " + id));
        productRepository.delete(product);
        return new ResponseStructure<>(HttpStatus.NO_CONTENT.value(), "Product deleted successfully", null);
    }
}
