package com.ty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ty.entity.Category;
import com.ty.responsestructure.ResponseStructure;
import com.ty.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<ResponseStructure<Category>> createCategory(@RequestBody Category category) {
        ResponseStructure<Category> response = categoryService.createCategory(category);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

 
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Category>>> getAllCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        ResponseStructure<List<Category>> response = categoryService.getAllCategories(page, size);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Category>> getCategoryById(@PathVariable Integer id) {
        ResponseStructure<Category> response = categoryService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<Category>> updateCategory(@PathVariable Integer id, @RequestBody Category categoryDetails) {
        ResponseStructure<Category> response = categoryService.updateCategory(id, categoryDetails);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteCategory(@PathVariable Integer id) {
        ResponseStructure<String> response = categoryService.deleteCategory(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

}
