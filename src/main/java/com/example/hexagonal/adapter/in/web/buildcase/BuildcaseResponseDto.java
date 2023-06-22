package com.example.hexagonal.adapter.in.web.buildcase;


import com.example.hexagonal.domain.buildcase.BuildCase;
import com.example.hexagonal.domain.employment.Employment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "구축 사례 응답 객체")
class BuildcaseResponseDto {
    private String buildCaseName;
    private String isVisible;
    private Long categoryId;
    private String thumbnailSrc;
    private List<String> detailSrcs = new ArrayList<>();
    private List<BuildCaseTableDto> tables;


    public static BuildcaseResponseDto toDto(BuildCase buildCase) {
        List<String> detailSrcs = buildCase.getDetailFiles().stream()
                .map(file -> file.getFilePath()).toList();
        List<BuildCaseTableDto> tableDtos = buildCase.getBuildCaseTable().stream()
                .map(table -> BuildCaseTableDto.toDto(table)).toList();

        return new BuildcaseResponseDto(buildCase.getBuildCaseName(),
                buildCase.getIsVisible().getCode(),
                buildCase.getCategory(),
                buildCase.getThumbnailFile().getFilePath(),
                detailSrcs,
                tableDtos
                );
    }

}
