package com.example.hexagonal.domain.employment;

import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.enums.DisplayType;
import com.example.hexagonal.global.enums.RoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employment {
    private final Long employmentId;

    private final String position;

    private final String occupation;

    private final String assignedTask;

    private final String qualification;

    private final DisplayType isVisible;

    private final LocalDateTime recruitStartDatetime;

    private final LocalDateTime recruitEndDatetime;

    private final EmploymentFile employmentFile;


    public static Employment withId(Long employmentId,
                                    String position,
                                    String occupation,
                                    String assignedTask,
                                    String qualification,
                                    DisplayType isVisible,
                                    LocalDateTime recruitStartDatetime,
                                    LocalDateTime recruitEndDatetime,
                                    EmploymentFile employmentFile) {
        return new Employment(employmentId, position, occupation, assignedTask,
                qualification, isVisible, recruitStartDatetime, recruitEndDatetime, employmentFile);
    }
    public static Employment withoutId(String position,
                                       String occupation,
                                       String assignedTask,
                                       String qualification,
                                       DisplayType isVisible,
                                       LocalDateTime recruitStartDatetime,
                                       LocalDateTime recruitEndDatetime,
                                       EmploymentFile employmentFile) {
        return new Employment(null, position, occupation, assignedTask,
                qualification, isVisible, recruitStartDatetime, recruitEndDatetime, employmentFile);
    }
}
