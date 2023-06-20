package com.example.hexagonal.application.port.out.user;

import com.example.hexagonal.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadUserPort {
    User readUserByMidasUserIdAndPassword(String midasUserId, String password);

    User readUserByUserId(Long userId);

    Page<User> readAllUser(Pageable pageable);

}
