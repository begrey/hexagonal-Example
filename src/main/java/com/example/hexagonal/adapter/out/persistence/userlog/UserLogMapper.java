package com.example.hexagonal.adapter.out.persistence.userlog;

import com.example.hexagonal.domain.userlog.UserLog;
import com.example.hexagonal.global.enums.LoginType;
import org.springframework.stereotype.Component;

@Component
public class UserLogMapper {
    UserLogJpaEntity toJpaEntity(UserLog userLog) {
        LoginType loginType = userLog.isLoginSuccess() == true ? LoginType.LOGIN_SUCCESS : LoginType.LOGIN_FAILED;
        return UserLogJpaEntity.builder()
                .midasUserId(userLog.getMidasUserId())
                .ipAddress(userLog.getIpAddress())
                .sessionId(userLog.getSessionId())
                .isLoginSuccess(loginType.getCode())
                .build();
    }

    UserLog toDomain(UserLogJpaEntity userLogJpaEntity) {
        boolean isLoginSuccess = userLogJpaEntity.getIsLoginSuccess() == "Y" ? true : false;
        return UserLog.withId(userLogJpaEntity.getId(),
                userLogJpaEntity.getMidasUserId(),
                userLogJpaEntity.getIpAddress(),
                userLogJpaEntity.getSessionId(),
                isLoginSuccess,
                userLogJpaEntity.getLoginDatetime());
    }
}
