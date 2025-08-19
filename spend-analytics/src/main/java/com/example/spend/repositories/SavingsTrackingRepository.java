package com.example.spend.repositories;

import com.example.spend.entities.SavingsTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsTrackingRepository extends JpaRepository<SavingsTracking, Long> {}

