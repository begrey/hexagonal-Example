package com.example.hexagonal.application.port.in.buildcase;

import com.example.hexagonal.adapter.in.web.buildcase.BuildCaseTableDto;
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

    @NotBlank
    private final MultipartFile thumbnailSrc;

    private final List<MultipartFile> buildCaseFile;

    private final List<BuildCaseTableDto> buildCaseTable;

    private final Long category;

    public static ModifyBuildCaseCommand create(String buildCaseName,
                                             DisplayType isVisible,
                                                MultipartFile thumbnailSrc,
                                             List<MultipartFile> buildCaseFile,
                                             List<BuildCaseTableDto> buildCaseTable,
                                             Long category) {
        return new ModifyBuildCaseCommand(buildCaseName, isVisible, thumbnailSrc, buildCaseFile, buildCaseTable, category);
    }
}
