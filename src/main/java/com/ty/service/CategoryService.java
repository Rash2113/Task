package com.ty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.entity.Category;
import com.ty.exception.ResourcesNotFoundException;
import com.ty.repository.CategoryRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
//Create a new category
	public ResponseStructure<Category> createCategory(Category category){
		Category save = categoryRepository.save(category);
		return new ResponseStructure<Category>(HttpStatus.CREATED.value(), "Category created successfully", save);
	}
	
//Get all category
	public ResponseStructure<List<Category>> getAllCategories(int page, int size) {
	    Page<Category> categoryPage = categoryRepository.findAll(PageRequest.of(page, size));
	    List<Category> categories = categoryPage.getContent();
	    return new ResponseStructure<>(HttpStatus.OK.value(), "Categories fetched successfully", categories);
	}

	
//Get category by id
		public ResponseStructure<Category> getById(Integer id){
			Optional<Category> opt = categoryRepository.findById(id);
			if(opt.isPresent()) {
				 return new ResponseStructure<>(HttpStatus.OK.value(), "Category found", opt.get());
	        } else {
	            return new ResponseStructure<>(HttpStatus.NOT_FOUND.value(), "Category not found", null);
	        }
	}
	
//Update category by id
		public ResponseStructure<Category> updateCategory(Integer id, Category categoryDetails) {
		    // Find the existing category by id
		    Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Category not found with ID: " + id));
		    category.setName(categoryDetails.getName());
		    Category updatedCategory = categoryRepository.save(category);
		    return new ResponseStructure<>(HttpStatus.OK.value(), "Category updated successfully", updatedCategory);
		}

		public ResponseStructure<String> deleteCategory(Integer id) {
		    Optional<Category> categoryOptional = categoryRepository.findById(id);
		    if (!categoryOptional.isPresent()) {
		        throw new ResourcesNotFoundException("Category not found with ID: " + id);
		    }

		    categoryRepository.deleteById(id);
		    return new ResponseStructure<>(HttpStatus.OK.value(), "Category deleted successfully", "Category deleted");
		}

}
