package com.example.spend.controllers;

import com.example.spend.entities.SavingsTracking;
import com.example.spend.services.SavingsTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/savings")
public class SavingsController {
    private final SavingsTrackingService savingsService;

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping
    public String list(Model model) {
        model.addAttribute("savings", savingsService.computeSavings());
        return "savings";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String create(@ModelAttribute SavingsTracking s) {
        savingsService.save(s);
        return "redirect:/savings";
    }
}

