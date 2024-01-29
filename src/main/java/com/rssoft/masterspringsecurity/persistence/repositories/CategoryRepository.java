package com.rssoft.masterspringsecurity.persistence.repositories;

import com.rssoft.masterspringsecurity.persistence.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
