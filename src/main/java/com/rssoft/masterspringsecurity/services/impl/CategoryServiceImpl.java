package com.rssoft.masterspringsecurity.services.impl;

import com.rssoft.masterspringsecurity.dto.SaveCategory;
import com.rssoft.masterspringsecurity.exceptions.ObjectNotFoundException;
import com.rssoft.masterspringsecurity.persistence.entities.Category;
import com.rssoft.masterspringsecurity.persistence.repositories.CategoryRepository;
import com.rssoft.masterspringsecurity.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {

        return categoryRepository.findAll(pageable);

    }

    @Override
    public Optional<Category> findById(Long categoryId) {

        return categoryRepository.findById(categoryId);

    }

    @Override
    public Category create(SaveCategory saveCategory) {

        Category category = new Category();
        category.setName(saveCategory.getName());
        category.setStatus(Category.CategoryStatus.ENABLED);

        return categoryRepository.save(category);

    }

    @Override
    public Category update(Long categoryId, SaveCategory saveCategory) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));

        category.setName(saveCategory.getName());

        return categoryRepository.save(category);

    }

    @Override
    public Category disabledById(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));

        category.setStatus(Category.CategoryStatus.DISABLED);

        return categoryRepository.save(category);

    }

}
