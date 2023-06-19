package com.example.hexagonal.application.port.in.user;

public interface ModifyUserUseCase {
    void modifyUser(Long userId, ModifyUserCommand modifyUserCommand);
}
