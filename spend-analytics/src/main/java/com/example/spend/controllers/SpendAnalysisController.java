package com.example.spend.controllers;

import com.example.spend.services.SpendAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class SpendAnalysisController {
    private final SpendAnalysisService analysisService;

    @GetMapping("/")
    public String root() { return "redirect:/analysis/dashboard"; }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping("/analysis/dashboard")
    public String dashboard(Model model,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        model.addAttribute("analytics", analysisService.aggregate(start, end));
        return "dashboard";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping("/analysis/suppliers")
    public String supplierAnalysis(Model model,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        model.addAttribute("analytics", analysisService.aggregate(start, end));
        return "analysis-suppliers";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping("/analysis/categories")
    public String categoryAnalysis(Model model,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        model.addAttribute("analytics", analysisService.aggregate(start, end));
        return "analysis-categories";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping("/analysis/trends")
    public String trends(Model model,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        model.addAttribute("analytics", analysisService.aggregate(start, end));
        return "analysis-trends";
    }
}

