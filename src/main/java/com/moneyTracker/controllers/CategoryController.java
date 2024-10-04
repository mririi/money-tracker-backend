package com.moneyTracker.controllers;

import com.moneyTracker.dtos.CategoryGetDto;
import com.moneyTracker.dtos.CategoryPatchDto;
import com.moneyTracker.dtos.CategoryPostDto;
import com.moneyTracker.entities.CategoryEntity;
import com.moneyTracker.enums.TransactionTypeEnum;
import com.moneyTracker.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public CategoryEntity createTransaction(@RequestBody CategoryPostDto categoryEntity) {
        return categoryService.create(categoryEntity);
    }

    @PatchMapping("/{id}")
    public void updateCategory(@PathVariable("id") final Integer id, @RequestBody CategoryPatchDto categoryPatchDto) {
        categoryService.update(id, categoryPatchDto);
    }

    @GetMapping("/{profileId}")
    public List<CategoryGetDto> getCategories(@PathVariable("profileId") final int profileId) {
        List<CategoryEntity> categoryEntities = categoryService.getCategories(profileId);
        return CategoryGetDto.from(categoryEntities);
    }

    @GetMapping("/profile/{id}/total-amount")
    public Double getTotalAmount(@PathVariable(value = "id") final Integer id,
                                 @RequestParam("type") final TransactionTypeEnum type) {
        return categoryService.getTotalAmount(id, type);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable("id") final Integer id) {
        categoryService.deleteCategory(id);
    }
}
