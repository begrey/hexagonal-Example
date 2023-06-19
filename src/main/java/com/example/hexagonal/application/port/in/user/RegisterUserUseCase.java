package com.example.hexagonal.application.port.in.user;

import org.springframework.stereotype.Component;

public interface RegisterUserUseCase {
    void registerUser(RegisterUserCommand registerUserCommand);
}
