package com.example.hexagonal.adapter.in.web.buildcase;

import com.example.hexagonal.global.enums.DisplayType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Schema(description = "구축 사례 요청 객체")
class BuildcaseRequestDto {

    @Getter
    public static class Post {
        private String buildCaseName;
        private DisplayType isVisible;
        private Long categoryId;
        private List<BuildCaseTableDto> tables;
        private List<String> banners;

    }

    @Getter
    public static class Put {
        private String buildCaseName;
        private DisplayType isVisible;
        private Long categoryId;
        private List<BuildCaseTableDto> tables;
        private List<String> banners;
    }


}
