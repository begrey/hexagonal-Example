package com.example.hexagonal.application.port.out.user;

import com.example.hexagonal.domain.user.User;

public interface ModifyUserPort {
    void modifyUser(Long userId, User change);
}
