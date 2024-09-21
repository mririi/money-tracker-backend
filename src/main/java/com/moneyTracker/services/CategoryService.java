package com.moneyTracker.services;

import com.moneyTracker.entities.CategoryEntity;
import com.moneyTracker.repositories.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryEntity create(CategoryEntity category) {
        return categoryJpaRepository.save(category);
    }

    public void update(CategoryEntity category) {
        categoryJpaRepository.save(category);
    }

    public List<CategoryEntity> getCategories(){
       return categoryJpaRepository.findAll();
    }
}
