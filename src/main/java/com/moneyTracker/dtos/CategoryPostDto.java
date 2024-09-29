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
public class CategoryPostDto {
    private String name;
    private TransactionTypeEnum type;
    private int profileId;
}
