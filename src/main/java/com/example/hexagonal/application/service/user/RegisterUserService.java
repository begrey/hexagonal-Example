package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.RegisterUserCommand;
import com.example.hexagonal.application.port.in.user.RegisterUserUseCase;
import com.example.hexagonal.application.port.out.user.RegisterUserPort;
import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
class RegisterUserService implements RegisterUserUseCase {

    private final RegisterUserPort registerUserPort;

    @Override
    public void registerUser(RegisterUserCommand registerUserCommand) {
        User user = User.withoutId(registerUserCommand.getMidasUserId(),
                    registerUserCommand.getPassword(),
                    registerUserCommand.getUserName(),
                    registerUserCommand.getTeam(),
                    registerUserCommand.getPhone(),
                    registerUserCommand.getAdminType(),
                    stringToRoleType(registerUserCommand.getUserRoles()));
        registerUserPort.registerUser(user);
    }

    public List<RoleType> stringToRoleType(List<String> userRoles) {
        return userRoles.stream()
                .map(userRole -> RoleType.of(userRole))
                .toList();
    }
}
