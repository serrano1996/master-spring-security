package com.rssoft.masterspringsecurity.controllers;


import com.rssoft.masterspringsecurity.dto.SaveCategory;
import com.rssoft.masterspringsecurity.dto.SaveProduct;
import com.rssoft.masterspringsecurity.persistence.entities.Category;
import com.rssoft.masterspringsecurity.persistence.entities.Product;
import com.rssoft.masterspringsecurity.services.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {

        Page<Category> categoryPage = categoryService.findAll(pageable);

        if (categoryPage.hasContent()) return ResponseEntity.ok(categoryPage);

        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findById(@PathVariable Long categoryId) {

        Optional<Category> category = categoryService.findById(categoryId);

        if (category.isPresent()) return ResponseEntity.ok(category.get());

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid SaveCategory saveCategory) {

        Category category = categoryService.create(saveCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable Long categoryId, @RequestBody @Valid SaveCategory saveCategory) {

        Category category = categoryService.update(categoryId, saveCategory);

        return ResponseEntity.ok(category);

    }

    @PutMapping("/{categoryId}/disable")
    public ResponseEntity<Category> disable(@PathVariable Long categoryId) {

        Category category = categoryService.disabledById(categoryId);

        return ResponseEntity.ok(category);

    }

}
