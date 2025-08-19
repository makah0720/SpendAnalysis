package com.example.spend.repositories;

import com.example.spend.entities.MaverickSpend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaverickSpendRepository extends JpaRepository<MaverickSpend, Long> {}

