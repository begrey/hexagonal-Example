package com.example.hexagonal.adapter.out.persistence.userlog;

import com.example.hexagonal.application.port.out.userlog.AddUserLogPort;
import com.example.hexagonal.application.port.out.userlog.LoadUserLogPort;
import com.example.hexagonal.domain.userlog.UserLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
public class UserLogPersistenceAdapter implements AddUserLogPort, LoadUserLogPort {

    private final UserLogRepository userLogRepository;
    private final UserLogMapper userLogMapper;

    @Override
    public Page<UserLog> readUserLogList(Pageable pageable) {
        Page<UserLogJpaEntity> userLogList = userLogRepository.findAll(pageable);
        return userLogList.map(userLog -> userLogMapper.toDomain(userLog));
    }

    @Override
    public Page<UserLog> readUserLogListByMidasUserId(Pageable pageable, String midasUserId) {
        Page<UserLogJpaEntity> userLogList = userLogRepository.findByMidasUserId(midasUserId, pageable);
        return userLogList.map(userLog -> userLogMapper.toDomain(userLog));
    }

    @Override
    public void AddUserLog(UserLog userLog) {
        userLogRepository.save(userLogMapper.toJpaEntity(userLog));
    }
}
