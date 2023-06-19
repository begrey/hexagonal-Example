package com.example.hexagonal.application.port.out.user;

import com.example.hexagonal.domain.user.User;

import java.util.List;

public interface RegisterUserPort {
    void registerUser(User user);

}
