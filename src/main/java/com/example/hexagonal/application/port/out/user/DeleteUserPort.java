package com.example.hexagonal.application.port.out.user;

public interface DeleteUserPort {
    void deleteUser(Long userId);

    void deleteUserRole(Long userId);
}
