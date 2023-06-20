package com.example.hexagonal.adapter.in.web.buildcase;

import com.example.hexagonal.domain.buildcase.BuildCaseTable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildCaseTableDto {
    private String title;
    private String content;

    public static BuildCaseTableDto toDto(BuildCaseTable buildCaseTable) {
        return new BuildCaseTableDto(buildCaseTable.getTitle(), buildCaseTable.getContent());
    }
}
