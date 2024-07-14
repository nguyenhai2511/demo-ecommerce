package com.demo.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	Page<Product> findAll(Pageable pageable);

}
