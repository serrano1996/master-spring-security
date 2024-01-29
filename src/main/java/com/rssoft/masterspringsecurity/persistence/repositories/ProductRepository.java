package com.rssoft.masterspringsecurity.persistence.repositories;

import com.rssoft.masterspringsecurity.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
