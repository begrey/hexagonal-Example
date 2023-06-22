package com.example.hexagonal.adapter.in.web.employment;

import com.example.hexagonal.global.enums.DisplayType;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


class EmploymentRequestDto {
    @Getter
    public static class Post {
        private String position;
        private String occupation;
        private String assignedTask;
        private String qualification;
        private DisplayType isVisible;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime recruitStartDatetime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime recruitEndDatetime;
    }

    @Getter
    public static class Put {
        private String position;
        private String occupation;
        private String assignedTask;
        private String qualification;
        private DisplayType isVisible;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime recruitStartDatetime;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime recruitEndDatetime;
    }
}
