package com.example.spend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "maverick_spend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaverickSpend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason;
    private Long spendTransactionId;
    private BigDecimal amount;
    private LocalDate detectedAt;
}

