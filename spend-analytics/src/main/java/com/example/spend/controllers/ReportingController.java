package com.example.spend.controllers;

import com.example.spend.dto.SpendReportDTO;
import com.example.spend.services.SpendReportingService;
import com.example.spend.utils.ExportUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportingController {
    private final SpendReportingService reportingService;

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping
    public String report(Model model,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        model.addAttribute("report", reportingService.generateReport(start, end));
        return "report";
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping(value = "/export/csv", produces = "text/csv")
    public ResponseEntity<byte[]> exportCsv(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        SpendReportDTO dto = reportingService.generateReport(start, end);
        StringBuilder sb = new StringBuilder();
        sb.append("Title,AsOfDate,TotalSpend,TransactionCount\n");
        sb.append(dto.getTitle()).append(',').append(dto.getAsOfDate()).append(',')
            .append(dto.getAnalytics().getTotalSpend()).append(',')
            .append(dto.getAnalytics().getTransactionCount()).append('\n');
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=spend-report.csv")
            .contentType(MediaType.parseMediaType("text/csv"))
            .contentLength(bytes.length)
            .body(bytes);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping(value = "/export/pdf")
    public ResponseEntity<byte[]> exportPdf(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        SpendReportDTO dto = reportingService.generateReport(start, end);
        byte[] bytes = ExportUtils.exportPdf(dto);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=spend-report.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .contentLength(bytes.length)
            .body(bytes);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @GetMapping(value = "/export/xlsx")
    public ResponseEntity<byte[]> exportExcel(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        SpendReportDTO dto = reportingService.generateReport(start, end);
        byte[] bytes = ExportUtils.exportExcel(dto);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=spend-report.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .contentLength(bytes.length)
            .body(bytes);
    }
}

