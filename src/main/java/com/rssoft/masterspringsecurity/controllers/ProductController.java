package com.rssoft.masterspringsecurity.controllers;


import com.rssoft.masterspringsecurity.dto.SaveProduct;
import com.rssoft.masterspringsecurity.persistence.entities.Product;
import com.rssoft.masterspringsecurity.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {

        Page<Product> productPage = productService.findAll(pageable);

        if (productPage.hasContent()) return ResponseEntity.ok(productPage);

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable Long productId) {

        Optional<Product> product = productService.findById(productId);

        if (product.isPresent()) return ResponseEntity.ok(product.get());

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid SaveProduct saveProduct) {

        Product product = productService.create(saveProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @RequestBody @Valid SaveProduct saveProduct) {

        Product product = productService.update(productId, saveProduct);

        return ResponseEntity.ok(product);

    }

    @PutMapping("/{productId}/disable")
    public ResponseEntity<Product> disable(@PathVariable Long productId) {

        Product product = productService.disabledById(productId);

        return ResponseEntity.ok(product);

    }

}
