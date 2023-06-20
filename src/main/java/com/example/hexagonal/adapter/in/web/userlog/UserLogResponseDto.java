package com.example.hexagonal.adapter.in.web.userlog;

import com.example.hexagonal.domain.userlog.UserLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "관리자 로그 응답 객체")
public class UserLogResponseDto {
    private String midasUserId;
    private String ipAddress;
    private String sessionId;
    private boolean isLoginSuccess;
    private String loginDateTime;

    public static UserLogResponseDto toDto(UserLog userLog) {
        return new UserLogResponseDto(userLog.getMidasUserId(),
                userLog.getIpAddress(),
                userLog.getSessionId(),
                userLog.isLoginSuccess(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(userLog.getLoginDatetime()));
    }
}
