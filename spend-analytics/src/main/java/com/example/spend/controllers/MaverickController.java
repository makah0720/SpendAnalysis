package com.example.spend.controllers;

import com.example.spend.entities.MaverickSpend;
import com.example.spend.services.SpendAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/analysis/maverick")
public class MaverickController {
    private final SpendAnalysisService analysisService;

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping
    public String list(Model model) {
        model.addAttribute("detections", Collections.emptyList());
        return "maverick";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @PostMapping("/detect")
    public String detect(Model model) {
        List<MaverickSpend> detections = analysisService.detectMaverickSpend();
        model.addAttribute("detections", detections);
        return "maverick";
    }
}

