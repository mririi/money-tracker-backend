package com.moneyTracker.dtos;

import com.moneyTracker.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPatchDto {
    private String name;
    private Double total;
    private TransactionTypeEnum type;
}
