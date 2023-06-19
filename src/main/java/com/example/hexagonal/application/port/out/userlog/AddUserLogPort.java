package com.example.hexagonal.application.port.out.userlog;

import com.example.hexagonal.domain.userlog.UserLog;

public interface AddUserLogPort {
    void AddUserLog(UserLog userLog);
}
