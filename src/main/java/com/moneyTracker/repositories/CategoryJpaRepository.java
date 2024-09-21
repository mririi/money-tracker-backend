package com.moneyTracker.repositories;

import com.moneyTracker.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {
}
