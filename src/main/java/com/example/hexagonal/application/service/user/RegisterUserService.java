package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.RegisterUserCommand;
import com.example.hexagonal.application.port.in.user.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
public class RegisterUserService implements RegisterUserUseCase {
    @Override
    public void registerUser(RegisterUserCommand registerUserCommand) {

    }
}
