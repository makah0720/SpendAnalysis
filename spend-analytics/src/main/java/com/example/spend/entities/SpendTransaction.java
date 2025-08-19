package com.example.spend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "spend_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionId;
    private String supplierName;
    private String categoryCode;
    private String categoryName;
    private String costCenter;
    private String currency;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private boolean approved;
    private boolean contracted;
}

