package com.example.spend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendAnalyticsDTO {
    private BigDecimal totalSpend;
    private Long transactionCount;
    private Map<String, BigDecimal> spendBySupplier;
    private Map<String, BigDecimal> spendByCategory;
    private Map<String, BigDecimal> monthlyTrend;
}

