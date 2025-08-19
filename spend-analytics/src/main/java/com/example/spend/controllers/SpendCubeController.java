package com.example.spend.controllers;

import com.example.spend.services.SpendCubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cube")
public class SpendCubeController {
    private final SpendCubeService cubeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/rebuild")
    public String rebuildCube() {
        cubeService.rebuildMonthlyCube();
        return "redirect:/analysis/dashboard";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/rebuild")
    public String rebuildCubeGet() {
        cubeService.rebuildMonthlyCube();
        return "redirect:/analysis/dashboard";
    }
}

