package com.rssoft.masterspringsecurity.services;

import com.rssoft.masterspringsecurity.dto.SaveProduct;
import com.rssoft.masterspringsecurity.persistence.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long productId);

    Product create(SaveProduct saveProduct);

    Product update(Long productId, SaveProduct saveProduct);

    Product disabledById(Long productId);

}
