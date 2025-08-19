package com.example.spend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spend_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String parentCode;
}

