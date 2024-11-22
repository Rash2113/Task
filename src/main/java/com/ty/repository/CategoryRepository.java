package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
