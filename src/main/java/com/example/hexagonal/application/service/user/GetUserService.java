package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.LoadUserUseCase;
import com.example.hexagonal.application.port.out.user.LoadUserPort;
import com.example.hexagonal.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
class GetUserService implements LoadUserUseCase {

    private final LoadUserPort loadUserPort;

    @Override
    public User loadUser(Long userId) {
        return loadUserPort.readUserByUserId(userId);
    }

    @Override
    public User loadUser(String midasUserId, String password) {
        return loadUserPort.readUserByMidasUserIdAndPassword(midasUserId, password);
    }

    @Override
    public Page<User> loadUsers(Pageable pageable) {
        return loadUserPort.readAllUser(pageable);
    }
}
