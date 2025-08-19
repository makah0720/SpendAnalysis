package com.example.spend.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavingsTrackingDTO {
    private String initiativeName;
    private String categoryCode;
    private String supplierName;
    private BigDecimal baselineSpend;
    private BigDecimal realizedSpend;
    private BigDecimal savings;
    private LocalDate period;
}

