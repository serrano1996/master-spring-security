package com.rssoft.masterspringsecurity.services.impl;

import com.rssoft.masterspringsecurity.dto.SaveProduct;
import com.rssoft.masterspringsecurity.exceptions.ObjectNotFoundException;
import com.rssoft.masterspringsecurity.persistence.entities.Category;
import com.rssoft.masterspringsecurity.persistence.entities.Product;
import com.rssoft.masterspringsecurity.persistence.repositories.ProductRepository;
import com.rssoft.masterspringsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {

        return productRepository.findAll(pageable);

    }

    @Override
    public Optional<Product> findById(Long productId) {

        return productRepository.findById(productId);

    }

    @Override
    public Product create(SaveProduct saveProduct) {

        Product product = new Product();
        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());
        product.setStatus(Product.ProductStatus.ENABLED);

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);

    }

    @Override
    public Product update(Long productId, SaveProduct saveProduct) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + productId));

        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);

    }

    @Override
    public Product disabledById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id: " + productId));

        product.setStatus(Product.ProductStatus.DISABLED);

        return productRepository.save(product);

    }

}
