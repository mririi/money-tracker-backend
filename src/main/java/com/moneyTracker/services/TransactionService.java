package com.moneyTracker.services;

import com.moneyTracker.dtos.TransactionPatchDto;
import com.moneyTracker.dtos.TransactionPostDto;
import com.moneyTracker.entities.TransactionEntity;
import com.moneyTracker.repositories.TransactionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionJpaRepository transactionJpaRepository;

    //todo
    public TransactionEntity createTransaction(TransactionPostDto transactionPostDto) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .amount(transactionPostDto.getAmount())
                .category(null)
                .date(LocalDate.parse(transactionPostDto.getDate()))
                .comment(transactionPostDto.getComment()).build();
        return transactionJpaRepository.save(transactionEntity);
    }

    public TransactionEntity getTransaction(Long id) {
        return transactionJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    public void deleteTransaction(Long id) {
        transactionJpaRepository.deleteById(id);
    }

    public Set<TransactionEntity> getTransactionsByCategoryId(int categoryId) {
        return new HashSet<>(transactionJpaRepository.findByCategoryId(categoryId));
    }

    //todo
    public TransactionEntity updateTransaction(long id, TransactionPatchDto transactionPatchDto) {
        TransactionEntity transactionEntity = transactionJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transactionEntity.setAmount(transactionPatchDto.getAmount());
        transactionEntity.setCategory(null);
        transactionEntity.setDate(LocalDate.parse(transactionPatchDto.getDate()));
        transactionEntity.setComment(transactionPatchDto.getComment());
        return transactionJpaRepository.save(transactionEntity);
    }
}
