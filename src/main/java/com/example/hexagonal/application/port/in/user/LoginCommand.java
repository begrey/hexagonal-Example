package com.example.hexagonal.application.port.in.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class LoginCommand {

    @NotBlank
    private final String midasUserId;

    @NotBlank
    private final String password;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

}
