package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.AddUserLogUseCase;
import com.example.hexagonal.application.port.in.user.LoginCommand;
import com.example.hexagonal.application.port.in.user.LoginUseCase;
import com.example.hexagonal.application.port.in.user.LogoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase, LogoutUseCase, AddUserLogUseCase {
    @Override
    public String login(LoginCommand loginCommand) {
        return null;
    }

    @Override
    public void addUserLog(String userId) {

    }
}
