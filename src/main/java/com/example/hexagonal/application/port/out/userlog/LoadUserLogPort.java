package com.example.hexagonal.application.port.out.userlog;

import com.example.hexagonal.domain.userlog.UserLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadUserLogPort {
    Page<UserLog> readUserLogList(Pageable pageable);

    Page<UserLog> readUserLogListByMidasUserId(Pageable pageable, String midasUserId);
}
