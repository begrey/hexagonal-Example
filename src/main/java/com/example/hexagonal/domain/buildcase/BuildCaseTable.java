package com.example.hexagonal.domain.buildcase;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildCaseTable {
    private final Long buildCaseId;

    private final String title;

    private final String content;


    public static BuildCaseTable withId(Long buildCaseId,
                                       String title,
                                       String content) {
        return new BuildCaseTable(buildCaseId, title, content);
    }
    public static BuildCaseTable withoutId(String title,
                                           String content) {
        return new BuildCaseTable(null, title, content);
    }
}
