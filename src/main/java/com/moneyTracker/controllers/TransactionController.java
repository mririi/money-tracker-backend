package com.moneyTracker.controllers;

import com.moneyTracker.dtos.TransactionPatchDto;
import com.moneyTracker.dtos.TransactionPostDto;
import com.moneyTracker.entities.TransactionEntity;
import com.moneyTracker.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/categories/{id}")
    public Set<TransactionEntity> getAllTransactionsByCategoryId(@PathVariable("id") final int id) {
        return transactionService.getTransactionsByCategoryId(id);
    }

    @PostMapping
    public TransactionEntity createTransaction(@RequestBody TransactionPostDto transactionPostDto) {
        return transactionService.createTransaction(transactionPostDto);
    }

    @PatchMapping("/{id}")
    public TransactionEntity updateTransaction(@PathVariable("id") final long id,
                                               @RequestBody TransactionPatchDto transactionPatchDto) {
        return transactionService.updateTransaction(id, transactionPatchDto);
    }
}
