package com.example.hexagonal.domain.buildcase;

import com.example.hexagonal.domain.employment.Employment;
import com.example.hexagonal.domain.employment.EmploymentFile;
import com.example.hexagonal.global.enums.DisplayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildCase {
    private final Long buildCaseId;

    private final String buildCaseName;

    private final DisplayType isVisible;

    private final BuildCaseFile thumbnailFile;

    private final List<BuildCaseFile> detailFiles;

    private final List<BuildCaseTable> buildCaseTable;

    private final Long category;


    public static BuildCase withId(Long employmentId,
                                    String buildCaseName,
                                   DisplayType isVisible,
                                   BuildCaseFile thumbnailFile,
                                   List<BuildCaseFile> detailFiles,
                                   List<BuildCaseTable> buildCaseTable,
                                   Long category) {
        return new BuildCase(employmentId, buildCaseName, isVisible, thumbnailFile, detailFiles, buildCaseTable, category);
    }
    public static BuildCase withoutId(String buildCaseName,
                                      DisplayType isVisible,
                                      BuildCaseFile thumbnailSrc,
                                      List<BuildCaseFile> detailFiles,
                                      List<BuildCaseTable> buildCaseTable,
                                      Long category) {
        return new BuildCase(null, buildCaseName, isVisible, thumbnailSrc, detailFiles, buildCaseTable, category);
    }
}
