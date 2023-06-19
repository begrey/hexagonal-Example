package com.example.hexagonal.application.port.in.user;

import com.example.hexagonal.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadUserUseCase {
    User loadUser(Long userId);
    User loadUser(String midasUserId, String password);

    Page<User> loadUsers(Pageable pageable);
}
