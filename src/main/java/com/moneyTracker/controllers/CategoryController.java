package com.moneyTracker.controllers;

import com.moneyTracker.entities.CategoryEntity;
import com.moneyTracker.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    public CategoryEntity createTransaction(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.create(categoryEntity);
    }
}
