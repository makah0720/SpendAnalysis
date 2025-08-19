package com.example.spend.repositories;

import com.example.spend.entities.SpendTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SpendTransactionRepository extends JpaRepository<SpendTransaction, Long> {
    List<SpendTransaction> findByTransactionDateBetween(LocalDate start, LocalDate end);
    List<SpendTransaction> findByApprovedFalseOrContractedFalse();
}

