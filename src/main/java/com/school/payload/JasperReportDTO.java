package com.school.payload;

import com.school.models.enums.ClassLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JasperReportDTO {
    private String studentName;
    private String schoolName;
    private String address;
    private String postalCode;
    private String schoolTel;
    private String schoolEmail;
    private ClassLevel studentClass;
}
