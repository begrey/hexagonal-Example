package com.example.hexagonal.global.enums;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum RoleType {
    ADMIN( "/admin/**"),
    EMPLOYMENT("/employments/**"),
    UPS_STS( "/build-case/UPS&STS/**"),
    COOLING("/build-case/COOLING/**"),
    LIGHTING("/build-case/LIGHTING/**"),
    RAILROAD("/build-case/RAILROAD/**");

    private String url;

//    private static final Map<String, String> ROLE_MAP = Collections.unmodifiableMap(
//            Stream.of(values()).collect(Collectors.toMap(RoleType::getRole, RoleType::name)));
//    public static RoleType of(final String role) {
//        return RoleType.valueOf(ROLE_MAP.get(role));
//    }

    RoleType(String url) {
        this.url = url;
    }
    public String returnUrl() {return url;}


}
