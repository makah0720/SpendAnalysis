package com.example.spend.services;

import com.example.spend.entities.SpendCube;
import com.example.spend.entities.SpendTransaction;
import com.example.spend.repositories.SpendCubeRepository;
import com.example.spend.repositories.SpendTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendCubeService {
    private final SpendCubeRepository cubeRepository;
    private final SpendTransactionRepository transactionRepository;

    @Transactional
    public void rebuildMonthlyCube() {
        cubeRepository.deleteAll();
        List<SpendTransaction> transactions = transactionRepository.findAll();
        Map<String, List<SpendTransaction>> grouped = transactions.stream()
            .collect(Collectors.groupingBy(t -> String.join("|",
                Optional.ofNullable(t.getSupplierName()).orElse("N/A"),
                Optional.ofNullable(t.getCategoryName()).orElse("Uncategorized"),
                YearMonth.from(t.getTransactionDate()).toString())));
        for (Map.Entry<String, List<SpendTransaction>> e : grouped.entrySet()) {
            BigDecimal total = e.getValue().stream().map(SpendTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            SpendCube cube = SpendCube.builder()
                .dimensionKey(e.getKey())
                .period(e.getValue().get(0).getTransactionDate().withDayOfMonth(1))
                .totalSpend(total)
                .transactionCount((long) e.getValue().size())
                .build();
            cubeRepository.save(cube);
        }
    }
}

