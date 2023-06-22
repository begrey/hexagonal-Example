package com.example.hexagonal.adapter.in.web.employment;

import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.global.enums.DisplayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
class EmploymentResponseDto {
    private Long employmentId;
    private String position;
    private String occupation;
    private String assignedTask;
    private String qualification;
    private String isVisible;
    private String recruitStartDatetime;
    private String recruitEndDatetime;
    private String filePath;

    public static EmploymentResponseDto toDto(Employment employment) {
        return new EmploymentResponseDto(employment.getEmploymentId(),
                employment.getPosition(),
                employment.getOccupation(),
                employment.getAssignedTask(),
                employment.getQualification(),
                employment.getIsVisible().getCode(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(employment.getRecruitStartDatetime()),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(employment.getRecruitEndDatetime()),
                employment.getEmploymentFile().getFilePath());
    }

}
