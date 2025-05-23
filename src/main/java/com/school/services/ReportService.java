package com.school.services;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.school.Repository.ClassRepository;
import com.school.Repository.ExamRepository;
import com.school.Repository.MarksRepository;
import com.school.Repository.StudentRepository;
import com.school.models.Exam;
import com.school.models.Marks;
import com.school.models.Student;
import com.school.payload.ReportDto;
import com.school.payload.SubjectMarkDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ReportService {
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final ExamRepository examRepository;
    private final MarksRepository marksRepository;

    public List<ReportDto> generateReportsForClass(Long classId, Long examId){
        List<Student> students = studentRepository.findByClassEntityId(classId);
        List<ReportDto> reports = new ArrayList<>();

        for (Student student : students) {
            reports.add(generateReportForStudent(student.getId(), examId));
        }
        return reports;
    }

    public ReportDto generateReportForStudent(Long studentId, Long examId){
        if(!studentRepository.existsById(studentId)){
            throw new IllegalArgumentException("Student does not exist");
        }
        if(!examRepository.existsById(examId)){
            throw new IllegalArgumentException("Exam does not exist");
        }
        Student student = studentRepository.findById(studentId).get();
        Exam exam = examRepository.findById(examId).get();

        List<Marks> marks = marksRepository.findByStudentIdAndExamId(studentId, examId);

        double total = 0;
        List<SubjectMarkDTO> subjectMarks = new ArrayList<>();

        for (Marks mark : marks) {
            double score = mark.getMarksObtained();
            total = total + score;

            SubjectMarkDTO subjectMarkDTO = new SubjectMarkDTO();
            subjectMarkDTO.setSubjectName(mark.getSubject().getName());
            subjectMarkDTO.setScore(score);
            subjectMarkDTO.setGrade(calculateGrade(score));

            subjectMarks.add(subjectMarkDTO);
        }

        double average = marks.isEmpty() ? 0 : total/marks.size();

        ReportDto report = new ReportDto();
        report.setStudentName(student.getName());
        report.setClassName(student.getClassEntity().getName());
        report.setExamName(exam.getName());
        report.setSubjectMarks(subjectMarks);
        report.setTotalScore(total);
        report.setAverageScore(average);
        report.setOverallGrade(calculateGrade(average));
        report.setOverallComment(generateComment(average));
        return report;
    }


    public ByteArrayInputStream generatePDFReportForStudent(Long studentId, Long examId) throws IOException {
        ReportDto report = generateReportForStudent(studentId, examId);

        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 18, Font.NORMAL);

        document.add(new Paragraph("Report Card", titleFont));
        document.add(new Paragraph("Student: "+ report.getStudentName(), normalFont));
        document.add(new Paragraph("Class: "+ report.getClassName(), normalFont));
        document.add(new Paragraph("Exam: "+ report.getExamName(), normalFont));

        PdfPTable table = new PdfPTable(3);
        table.addCell("Subject");
        table.addCell("Score");
        table.addCell("Grade");

        for (SubjectMarkDTO subject : report.getSubjectMarks()) {
            table.addCell(subject.getSubjectName());
            table.addCell(String.valueOf(subject.getScore()));
            table.addCell(String.valueOf(subject.getGrade()));
        }

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Total Score: " + report.getTotalScore(), normalFont));
        document.add(new Paragraph("Average Score: " + report.getAverageScore(), normalFont));
        document.add(new Paragraph("Overall Grade: " + report.getOverallGrade(), normalFont));
        document.add(new Paragraph("Overall Comment: " + report.getOverallComment(), normalFont));
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private String calculateGrade(double score){
        if(score >= 80){return "A";}
        if(score >= 70){return "B";}
        if(score >= 50){return "C";}
        if(score >= 40){return "D";}
        return "E";
    }

    private String generateComment(double average){
        if(average >= 80){return "Excellent Performance";}
        if(average >= 70){return "Very Good";}
        if(average >= 50){return "Good effort";}
        if(average >= 40){return "Fair";}
        return "Needs Improvement";
    }
}
