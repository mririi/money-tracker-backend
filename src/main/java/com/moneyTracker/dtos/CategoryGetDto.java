package com.moneyTracker.dtos;

import com.moneyTracker.entities.CategoryEntity;
import com.moneyTracker.entities.ProfileEntity;
import com.moneyTracker.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGetDto {
    private int id;
    private String name;
    private Double total;
    private TransactionTypeEnum type;
    private ProfileEntity profile;

    public static List<CategoryGetDto> from(List<CategoryEntity> categoryEntityList) {
        return categoryEntityList.stream()
            .map(categoryEntity -> CategoryGetDto.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .total(categoryEntity.getTotal())
                .type(categoryEntity.getType())
                .profile(categoryEntity.getProfileEntity())
                .build())
            .collect(Collectors.toList());
    }
}
