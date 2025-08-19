package com.example.spend.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendReportDTO {
    private String title;
    private LocalDate asOfDate;
    private SpendAnalyticsDTO analytics;
    private List<SavingsTrackingDTO> savings;
}

