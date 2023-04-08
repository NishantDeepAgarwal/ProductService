package com.javaguides.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaguides.productservice.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

}
