package com.example.hexagonal.domain.buildcase;

import com.example.hexagonal.domain.employment.EmploymentFile;
import com.example.hexagonal.global.enums.FileType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildCaseFile {
    private final Long fileId;

    private final String filePath;

    private final String realName;

    private final String tempName;

    private final String extension;

    private final FileType fileType;

    private final Long fileSize;


    public static BuildCaseFile withId(Long fileId,
                                        String filePath,
                                        String realName,
                                        String tempName,
                                        String extension,
                                        FileType fileType,
                                        Long fileSize) {
        return new BuildCaseFile(fileId, filePath, realName, tempName, extension, fileType, fileSize);
    }
    public static BuildCaseFile withoutId(String filePath,
                                           String realName,
                                           String tempName,
                                           String extension,
                                          FileType fileType,
                                           Long fileSize) {
        return new BuildCaseFile(null, filePath, realName, tempName, extension, fileType, fileSize);
    }

    public boolean isThumbNail() {
        return this.fileType.equals(FileType.THUMBNAIL) ? true : false;
    }
}
