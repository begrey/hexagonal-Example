package com.example.hexagonal.application.port.in.user;

import javax.servlet.http.HttpServletRequest;

public interface AddUserLogUseCase {
    void addUserLog(String midasUserId, HttpServletRequest request, boolean loginSuccess);
}
