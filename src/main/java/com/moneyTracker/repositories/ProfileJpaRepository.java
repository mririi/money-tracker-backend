package com.moneyTracker.repositories;

import com.moneyTracker.entities.ProfileEntity;
import com.moneyTracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByUser(User user);
}
