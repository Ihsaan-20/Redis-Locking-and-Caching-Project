package com.example.redis_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.redis_project.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

