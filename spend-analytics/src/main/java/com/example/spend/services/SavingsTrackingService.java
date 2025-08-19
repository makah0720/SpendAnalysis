package com.example.spend.services;

import com.example.spend.dto.SavingsTrackingDTO;
import com.example.spend.entities.SavingsTracking;
import com.example.spend.repositories.SavingsTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SavingsTrackingService {
    private final SavingsTrackingRepository savingsRepository;

    @Transactional
    public SavingsTracking save(SavingsTracking s) {
        return savingsRepository.save(s);
    }

    @Transactional(readOnly = true)
    public List<SavingsTrackingDTO> computeSavings() {
        return savingsRepository.findAll().stream().map(s -> {
            BigDecimal savings = s.getBaselineSpend().subtract(s.getRealizedSpend());
            return SavingsTrackingDTO.builder()
                .initiativeName(s.getInitiativeName())
                .categoryCode(s.getCategoryCode())
                .supplierName(s.getSupplierName())
                .baselineSpend(s.getBaselineSpend())
                .realizedSpend(s.getRealizedSpend())
                .savings(savings)
                .period(s.getPeriod())
                .build();
        }).collect(Collectors.toList());
    }
}

