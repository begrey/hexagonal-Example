package com.example.hexagonal.application.port.in.buildcase;

import com.example.hexagonal.domain.buildcase.BuildCaseTable;
import com.example.hexagonal.global.enums.DisplayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ModifyBuildCaseCommand {
    private final String buildCaseName;

    @NotBlank
    private final DisplayType isVisible;

    private final MultipartFile thumbnail;

    private final List<MultipartFile> buildCaseFile;

    private final List<BuildCaseTable> buildCaseTable;

    private final Long category;

    public static ModifyBuildCaseCommand create(String buildCaseName,
                                             DisplayType isVisible,
                                                MultipartFile thumbnailSrc,
                                             List<MultipartFile> buildCaseFile,
                                             List<BuildCaseTable> buildCaseTables,
                                             Long category) {
        return new ModifyBuildCaseCommand(buildCaseName, isVisible, thumbnailSrc, buildCaseFile, buildCaseTables, category);
    }
}
