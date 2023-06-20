package com.example.hexagonal.application.port.in.employment;

import com.example.hexagonal.global.enums.DisplayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ModifyEmploymentCommand {

    private final String position;

    private final String occupation;

    private final String assignedTask;

    private final String qualification;

    private final LocalDateTime recruitStartDatetime;

    private final LocalDateTime recruitEndDatetime;

    @NotBlank
    private final DisplayType isVisible;

    @NotNull
    private final MultipartFile employmentFile;

    public static ModifyEmploymentCommand create(String position,
                                              String occupation,
                                              String assignedTask,
                                              String qualification,
                                              DisplayType isVisible,
                                                 LocalDateTime recruitStartDatetime,
                                                 LocalDateTime recruitEndDatetime,
                                              MultipartFile multipartFile) {
        return new ModifyEmploymentCommand(position, occupation, assignedTask, qualification, recruitStartDatetime, recruitEndDatetime, isVisible, multipartFile);
    }
}
