package com.moneyTracker.repositories;

import com.moneyTracker.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    Set<TransactionEntity> findByCategoryId(int categoryId);
}
