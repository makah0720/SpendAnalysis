package com.example.spend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spend_dashboard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendDashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String widgetName;
    private String widgetConfigJson;
}

