package com.moneyTracker.services;

import com.moneyTracker.dtos.CategoryPatchDto;
import com.moneyTracker.dtos.CategoryPostDto;
import com.moneyTracker.entities.CategoryEntity;
import com.moneyTracker.entities.ProfileEntity;
import com.moneyTracker.enums.TransactionTypeEnum;
import com.moneyTracker.repositories.CategoryJpaRepository;
import com.moneyTracker.repositories.ProfileJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryJpaRepository categoryJpaRepository;
    private final ProfileJpaRepository profileJpaRepository;

    public CategoryEntity create(CategoryPostDto category) {
        Optional<ProfileEntity> profileEntity = profileJpaRepository.findById(category.getProfileId());
        if (profileEntity.isEmpty()) {
            throw new RuntimeException("Profile not found");
        }
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(category.getName())
                .type(category.getType())
                .profileEntity(profileEntity.get())
                .build();
        return categoryJpaRepository.save(categoryEntity);
    }

    public void update(Integer id, CategoryPatchDto category) {
        CategoryEntity categoryEntity = categoryJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryEntity.setName(category.getName());
        categoryEntity.setType(category.getType());
        categoryEntity.setTotal(category.getTotal());
        categoryJpaRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getCategories(int profileId){
       return categoryJpaRepository.findAllByProfileEntityId(profileId);
    }

    public Double getTotalAmount(Integer profileId, TransactionTypeEnum type) {
        Double totalAmount = categoryJpaRepository.sumAmountByCategoryTypeAndProfileEntityId(type, profileId);
        return totalAmount != null ? totalAmount : 0.0 ;
    }

    public void deleteCategory(Integer id) {
        categoryJpaRepository.deleteById(id);
    }
}
