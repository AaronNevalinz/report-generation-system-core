package com.school.controllers;

import com.school.payload.ReportDto;
import com.school.services.ReportService;
import com.school.utils.OperationReturnObject;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/jasper/{studentId}")
    public ResponseEntity<ByteArrayResource> generateJasperReport(@PathVariable Long studentId) {
        try {
            byte[] reportBytes = reportService.generateReportFromJasper(studentId);
            ByteArrayResource resource = new ByteArrayResource(reportBytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=student_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(reportBytes.length)
                    .body(resource);

        } catch (Exception e) { // Catch any other unexpected exceptions
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ByteArrayResource(("An unexpected error occurred: " + Arrays.toString(e.getMessage().getBytes())).getBytes()));
        }
    }

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

//    generate single student report using jasperreport
    

//    Generate a single student pdf
    @GetMapping("/studentpdf/{studentId}")
    public ResponseEntity<InputStreamResource> generateStudentReportPdf(@PathVariable Long studentId, @RequestParam Long examId) throws IOException {
        ByteArrayInputStream bis = reportService.generatePDFReportForStudent(studentId, examId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));

    }
}
