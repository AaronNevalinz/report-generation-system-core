package com.school.controllers;

import com.school.payload.ReportDto;
import com.school.services.ReportService;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;

//    Generate class report for each and every student in a class
    @GetMapping("/class/{classId}")
    public OperationReturnObject generateClassReport(@RequestParam Long examId, @PathVariable Long classId) {

        List<ReportDto> classReports = reportService.generateReportsForClass(classId, examId);
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setCodeAndMessageAndReturnObject(200, "All reports", classReports);
        return operationReturnObject;
    }
//    Generate a single student report
    @GetMapping("/student/{studentId}")
    public OperationReturnObject generateStudentReport(@PathVariable Long studentId, @RequestParam Long examId) {
        ReportDto reportDto = reportService.generateReportForStudent(studentId, examId);
        OperationReturnObject operationReturnObject = new OperationReturnObject();
        operationReturnObject.setCodeAndMessageAndReturnObject(200, "All reports", reportDto);
        return operationReturnObject;
    }

//    Generate a single student pdf
    @GetMapping("/studentpdf/{studentId}")
    public ResponseEntity<InputStreamResource> generateStudentReportPdf(@PathVariable Long studentId, @RequestParam Long examId) throws IOException {
        ByteArrayInputStream bis = reportService.generatePDFReportForStudent(studentId, examId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));

    }
}
