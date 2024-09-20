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
public class TransactionPostDto {
    private Double amount;
    private String category;
    private TransactionTypeEnum type;
    private String date;
    private String comment;
    private int profileId;
}
