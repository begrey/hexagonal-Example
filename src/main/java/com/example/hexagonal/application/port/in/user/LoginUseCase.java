package com.example.hexagonal.application.port.in.user;

import org.springframework.security.core.Authentication;

public interface LoginUseCase {
    String login(LoginCommand loginCommand);

}
