package com.example.hexagonal.global.enums;

import lombok.Getter;

@Getter
public enum DisplayType {
    DISPLAY_ON("Y"),
    DISPLAY_OFF("N");

    private String code;

    DisplayType(String code) {
        this.code = code;
    }
}
