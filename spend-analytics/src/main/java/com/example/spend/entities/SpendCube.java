package com.example.spend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "spend_cube")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendCube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dimensionKey;
    private LocalDate period;
    private BigDecimal totalSpend;
    private Long transactionCount;
}

