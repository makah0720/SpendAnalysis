package com.example.spend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "savings_tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String initiativeName;
    private String categoryCode;
    private String supplierName;
    private BigDecimal baselineSpend;
    private BigDecimal realizedSpend;
    private LocalDate period;
}

