package com.ebusiness.SpringBoot_Microservices_eBusiness.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ebusiness.SpringBoot_Microservices_eBusiness.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	List<Product> findAll();
	Product findById(int i);
	List<Product> findByNameLike(String s);
	List<Product> findByPriceGreaterThan(int priceLimit);
	Product save(Product p);
}



