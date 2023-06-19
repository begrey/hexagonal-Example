package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.DeleteUserUseCase;
import com.example.hexagonal.application.port.out.user.DeleteUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
class DeleteUserService implements DeleteUserUseCase {

    private final DeleteUserPort deleteUserPort;
    @Override
    public void deleteUser(Long userId) {
        deleteUserPort.deleteUser(userId);
    }
}
