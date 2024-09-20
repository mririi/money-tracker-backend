package com.moneyTracker.entities;


import com.moneyTracker.enums.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private String category;
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;
    private LocalDate date;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private ProfileEntity profileEntity;
}
