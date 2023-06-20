package com.example.hexagonal.application.port.in.buildcase;

import com.example.hexagonal.adapter.in.web.buildcase.BuildCaseTableDto;
import com.example.hexagonal.application.port.in.employment.AddEmploymentCommand;
import com.example.hexagonal.domain.buildcase.BuildCaseFile;
import com.example.hexagonal.domain.buildcase.BuildCaseTable;
import com.example.hexagonal.global.enums.DisplayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AddBuildCaseCommand {
    private final String buildCaseName;

    @NotBlank
    private final DisplayType isVisible;

    @NotBlank
    private final MultipartFile thumbnail;

    private final List<MultipartFile> buildCaseFile;

    private final List<BuildCaseTableDto> buildCaseTable;

    private final Long category;

    public static AddBuildCaseCommand create(String buildCaseName,
                                             DisplayType isVisible,
                                             MultipartFile thumbnail,
                                             List<MultipartFile> buildCaseFile,
                                             List<BuildCaseTableDto> buildCaseTable,
                                             Long category) {
        return new AddBuildCaseCommand(buildCaseName, isVisible, thumbnail, buildCaseFile, buildCaseTable, category);
    }
}
