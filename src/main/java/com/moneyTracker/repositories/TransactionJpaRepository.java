package com.moneyTracker.repositories;

import com.moneyTracker.entities.TransactionEntity;
import com.moneyTracker.enums.TransactionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
    @Query("SELECT SUM(t.amount) FROM TransactionEntity t WHERE t.profileEntity.id = :profileId AND t.type = :type")
    Double sumAmountByProfileEntityIdAndType(@Param("profileId") int profileId, @Param("type") TransactionTypeEnum type);

    Set<TransactionEntity> findByProfileEntityId(int profileId);
}
