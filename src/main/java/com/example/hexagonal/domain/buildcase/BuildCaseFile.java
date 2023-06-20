package com.example.hexagonal.domain.buildcase;

import com.example.hexagonal.domain.employment.EmploymentFile;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildCaseFile {
    private final Long fileId;

    private final String filePath;

    private final String realName;

    private final String tempName;

    private final String extension;

    private final Long fileSize;


    public static BuildCaseFile withId(Long fileId,
                                        String filePath,
                                        String realName,
                                        String tempName,
                                        String extension,
                                        Long fileSize) {
        return new BuildCaseFile(fileId, filePath, realName, tempName, extension, fileSize);
    }
    public static BuildCaseFile withoutId(String filePath,
                                           String realName,
                                           String tempName,
                                           String extension,
                                           Long fileSize) {
        return new BuildCaseFile(null, filePath, realName, tempName, extension, fileSize);
    }
}
