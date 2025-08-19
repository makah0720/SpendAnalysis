package com.example.spend.services;

import com.example.spend.dto.SavingsTrackingDTO;
import com.example.spend.dto.SpendAnalyticsDTO;
import com.example.spend.dto.SpendReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpendReportingService {
    private final SpendAnalysisService analysisService;
    private final SavingsTrackingService savingsService;

    @Transactional(readOnly = true)
    public SpendReportDTO generateReport(LocalDate start, LocalDate end) {
        SpendAnalyticsDTO analytics = analysisService.aggregate(start, end);
        List<SavingsTrackingDTO> savings = savingsService.computeSavings();
        return SpendReportDTO.builder()
            .title("Spend Report")
            .asOfDate(LocalDate.now())
            .analytics(analytics)
            .savings(savings)
            .build();
    }
}

