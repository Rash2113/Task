package com.ty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
