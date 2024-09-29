package com.moneyTracker.repositories;

import com.moneyTracker.entities.CategoryEntity;
import com.moneyTracker.enums.TransactionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Integer> {

    @Query("SELECT SUM(t.total) FROM CategoryEntity t WHERE t.type = :type and t.profileEntity.id = :profileId")
    Double sumAmountByCategoryTypeAndProfileEntityId(@Param("type") TransactionTypeEnum type, @Param("profileId") Integer profileId);
    List<CategoryEntity> findAllByProfileEntityId(int profileId);
}
