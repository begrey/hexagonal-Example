package com.example.hexagonal.domain.userlog;

import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.enums.RoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLog {
    private final Long id;

    private final String midasUserId;

    private final String ipAddress;

    private final String SessionId;

    private final boolean isLoginSuccess;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final LocalDateTime loginDatetime;


    public static UserLog withId(Long id,
                              String midasUserId,
                              String ipAddress,
                              String SessionId,
                                 boolean isLoginSuccess,
                                 LocalDateTime loginDatetime) {
        return new UserLog(id, midasUserId, ipAddress, SessionId, isLoginSuccess, loginDatetime);
    }
    public static UserLog withoutId(String midasUserId,
                                    String ipAddress,
                                    String sessionId,
                                    boolean isLoginSuccess) {
        return new UserLog(null, midasUserId, ipAddress, sessionId, isLoginSuccess, null);
    }
}
