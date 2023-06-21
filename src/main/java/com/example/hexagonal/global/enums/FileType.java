package com.example.hexagonal.global.enums;

import lombok.Getter;

@Getter
public enum FileType {
    THUMBNAIL("thumbnail"),
    DETAIL("detail");

    private String code;

    FileType(String code) {
        this.code = code;
    }
}
