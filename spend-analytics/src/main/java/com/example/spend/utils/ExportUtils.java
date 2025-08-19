package com.example.spend.utils;

import com.example.spend.dto.SavingsTrackingDTO;
import com.example.spend.dto.SpendReportDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExportUtils {

    public static byte[] exportPdf(SpendReportDTO dto) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            document.add(new Paragraph(dto.getTitle(), titleFont));
            document.add(new Paragraph("As of: " + dto.getAsOfDate()));
            document.add(new Paragraph("Total Spend: " + dto.getAnalytics().getTotalSpend()));
            document.add(new Paragraph("Transactions: " + dto.getAnalytics().getTransactionCount()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(7);
            String[] headers = {"Initiative", "Category", "Supplier", "Baseline", "Realized", "Savings", "Period"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h));
                cell.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(cell);
            }
            List<SavingsTrackingDTO> rows = dto.getSavings();
            for (SavingsTrackingDTO s : rows) {
                table.addCell(s.getInitiativeName());
                table.addCell(s.getCategoryCode());
                table.addCell(s.getSupplierName());
                table.addCell(String.valueOf(s.getBaselineSpend()));
                table.addCell(String.valueOf(s.getRealizedSpend()));
                table.addCell(String.valueOf(s.getSavings()));
                table.addCell(String.valueOf(s.getPeriod()));
            }
            document.add(table);
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to export PDF", e);
        }
    }

    public static byte[] exportExcel(SpendReportDTO dto) {
        try (XSSFWorkbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = wb.createSheet("Spend Report");
            int rowIdx = 0;
            org.apache.poi.ss.usermodel.Row r0 = sheet.createRow(rowIdx++);
            r0.createCell(0).setCellValue(dto.getTitle());
            org.apache.poi.ss.usermodel.Row r1 = sheet.createRow(rowIdx++);
            r1.createCell(0).setCellValue("As of");
            r1.createCell(1).setCellValue(dto.getAsOfDate().toString());
            org.apache.poi.ss.usermodel.Row r2 = sheet.createRow(rowIdx++);
            r2.createCell(0).setCellValue("Total Spend");
            r2.createCell(1).setCellValue(dto.getAnalytics().getTotalSpend().doubleValue());
            org.apache.poi.ss.usermodel.Row r3 = sheet.createRow(rowIdx++);
            r3.createCell(0).setCellValue("Transactions");
            r3.createCell(1).setCellValue(dto.getAnalytics().getTransactionCount());

            rowIdx++;
            org.apache.poi.ss.usermodel.Row h = sheet.createRow(rowIdx++);
            String[] headers = {"Initiative", "Category", "Supplier", "Baseline", "Realized", "Savings", "Period"};
            for (int i = 0; i < headers.length; i++) {
                h.createCell(i).setCellValue(headers[i]);
            }
            for (SavingsTrackingDTO s : dto.getSavings()) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(s.getInitiativeName());
                row.createCell(1).setCellValue(s.getCategoryCode());
                row.createCell(2).setCellValue(s.getSupplierName());
                row.createCell(3).setCellValue(s.getBaselineSpend().doubleValue());
                row.createCell(4).setCellValue(s.getRealizedSpend().doubleValue());
                row.createCell(5).setCellValue(s.getSavings().doubleValue());
                row.createCell(6).setCellValue(s.getPeriod().toString());
            }
            for (int i = 0; i < headers.length; i++) sheet.autoSizeColumn(i);
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to export Excel", e);
        }
    }
}

