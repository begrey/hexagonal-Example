package com.example.hexagonal.domain.employment;

import com.example.hexagonal.global.enums.DisplayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EmploymentFile {
    private final Long fileId;

    private final String filePath;

    private final String realName;

    private final String tempName;

    private final String extension;

    private final Long fileSize;


    public static EmploymentFile withId(Long fileId,
                                    String filePath,
                                    String realName,
                                    String tempName,
                                    String extension,
                                        Long fileSize) {
        return new EmploymentFile(fileId, filePath, realName, tempName, extension, fileSize);
    }
    public static EmploymentFile withoutId(String filePath,
                                           String realName,
                                           String tempName,
                                           String extension,
                                           Long fileSize) {
        return new EmploymentFile(null, filePath, realName, tempName, extension, fileSize);
    }
}
