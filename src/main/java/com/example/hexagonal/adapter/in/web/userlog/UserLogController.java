package com.example.hexagonal.adapter.in.web.userlog;

import com.example.hexagonal.application.port.in.user.LoadUserUseCase;
import com.example.hexagonal.application.port.in.userlog.LoadUserLogUseCase;
import com.example.hexagonal.domain.userlog.UserLog;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "관리자 로그 API")
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@RestController
class UserLogController {

    public final LoadUserLogUseCase loadUserUseCase;

    @GetMapping("/logs")
    public Page<UserLogResponseDto> getUserLogList(Pageable pageable) {
        Page<UserLog> userLogs = loadUserUseCase.loadUsers(pageable);
        return userLogs.map(userLog -> UserLogResponseDto.toDto(userLog));
    }
    @GetMapping("/logs/search")
    public Page<UserLogResponseDto> SearchUserLogListByUserId(Pageable pageable, @RequestParam("id") String midasUserId) {
        Page<UserLog> userLogs = loadUserUseCase.loadUsersByUserId(midasUserId, pageable);
        return userLogs.map(userLog -> UserLogResponseDto.toDto(userLog));
    }
}
