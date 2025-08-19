package com.example.spend.services;

import com.example.spend.dto.SpendAnalyticsDTO;
import com.example.spend.entities.MaverickSpend;
import com.example.spend.entities.SpendTransaction;
import com.example.spend.repositories.MaverickSpendRepository;
import com.example.spend.repositories.SpendTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendAnalysisService {
    private final SpendTransactionRepository transactionRepository;
    private final MaverickSpendRepository maverickSpendRepository;

    @Transactional(readOnly = true)
    public SpendAnalyticsDTO aggregate(LocalDate start, LocalDate end) {
        List<SpendTransaction> transactions = (start != null && end != null)
            ? transactionRepository.findByTransactionDateBetween(start, end)
            : transactionRepository.findAll();

        BigDecimal total = transactions.stream()
            .map(SpendTransaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, BigDecimal> bySupplier = transactions.stream()
            .collect(Collectors.groupingBy(SpendTransaction::getSupplierName,
                Collectors.mapping(SpendTransaction::getAmount,
                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Map<String, BigDecimal> byCategory = transactions.stream()
            .collect(Collectors.groupingBy(t -> Optional.ofNullable(t.getCategoryName()).orElse("Uncategorized"),
                Collectors.mapping(SpendTransaction::getAmount,
                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Map<String, BigDecimal> trend = transactions.stream()
            .collect(Collectors.groupingBy(t -> YearMonth.from(t.getTransactionDate()).toString(),
                Collectors.mapping(SpendTransaction::getAmount,
                    Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        return SpendAnalyticsDTO.builder()
            .totalSpend(total)
            .transactionCount((long) transactions.size())
            .spendBySupplier(bySupplier)
            .spendByCategory(byCategory)
            .monthlyTrend(trend)
            .build();
    }

    @Transactional
    public List<MaverickSpend> detectMaverickSpend() {
        List<SpendTransaction> suspicious = transactionRepository.findByApprovedFalseOrContractedFalse();
        List<MaverickSpend> detections = new ArrayList<>();
        for (SpendTransaction t : suspicious) {
            MaverickSpend m = MaverickSpend.builder()
                .reason(!t.isApproved() ? "Unapproved transaction" : "Uncontracted supplier")
                .spendTransactionId(t.getId())
                .amount(t.getAmount())
                .detectedAt(LocalDate.now())
                .build();
            detections.add(m);
        }
        return maverickSpendRepository.saveAll(detections);
    }
}

