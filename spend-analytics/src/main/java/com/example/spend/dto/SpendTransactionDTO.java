package com.example.spend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendTransactionDTO {
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

