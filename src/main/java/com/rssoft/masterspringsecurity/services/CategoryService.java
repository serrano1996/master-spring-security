package com.rssoft.masterspringsecurity.services;

import com.rssoft.masterspringsecurity.dto.SaveCategory;
import com.rssoft.masterspringsecurity.persistence.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long categoryId);

    Category create(SaveCategory saveCategory);

    Category update(Long categoryId, SaveCategory saveCategory);

    Category disabledById(Long categoryId);

}
