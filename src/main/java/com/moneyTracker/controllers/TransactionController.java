package com.moneyTracker.controllers;

import com.moneyTracker.dtos.TransactionPatchDto;
import com.moneyTracker.dtos.TransactionPostDto;
import com.moneyTracker.entities.TransactionEntity;
import com.moneyTracker.enums.TransactionTypeEnum;
import com.moneyTracker.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/profile/{id}")
    public Set<TransactionEntity> getAllTransactionsByProfileId(@PathVariable("id") final int id) {
        return transactionService.getTransactionsByProfileId(id);
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

    @GetMapping("/profile/{id}/total-amount")
    public Double getTotalAmount(@PathVariable("id") final int id, @RequestParam("type") final TransactionTypeEnum type) {
        return transactionService.getTotalAmount(id, type);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable("id") final Long id) {
        transactionService.deleteTransaction(id);
    }

    @GetMapping("/{id}")
    public TransactionEntity getTransaction(@PathVariable("id") final Long id) {
        return transactionService.getTransaction(id);
    }
}
