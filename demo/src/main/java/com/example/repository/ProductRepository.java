package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Product;
import com.example.model.User;

	@Repository("productrepository")
	public interface ProductRepository extends JpaRepository<Product, Long> {
		 Product findByproductId(int productId);
	}


