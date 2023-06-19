package com.example.hexagonal.application.service.userlog;

import com.example.hexagonal.adapter.in.web.userlog.UserLogResponseDto;
import com.example.hexagonal.application.port.in.userlog.LoadUserLogUseCase;
import com.example.hexagonal.application.port.out.userlog.LoadUserLogPort;
import com.example.hexagonal.domain.userlog.UserLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class GetUserLogService implements LoadUserLogUseCase {
    private final LoadUserLogPort loadUserLogPort;

    @Override
    public Page<UserLog> loadUsers(Pageable pageable) {
        Page<UserLog> userLogs = loadUserLogPort.readUserLogList(pageable);
        return userLogs;
    }

    @Override
    public Page<UserLog> loadUsersByUserId(String midasUserId, Pageable pageable) {
        Page<UserLog> userLogs = loadUserLogPort.readUserLogListByMidasUserId(pageable, midasUserId);
        return userLogs;
    }
}
