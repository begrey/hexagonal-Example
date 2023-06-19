package com.example.hexagonal.application.port.in.userlog;

import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.domain.userlog.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadUserLogUseCase {
//    UserLog loadUser(Long userId);
    Page<UserLog> loadUsers(Pageable pageable);
    Page<UserLog> loadUsersByUserId(String midasUserId, Pageable pageable);
}
