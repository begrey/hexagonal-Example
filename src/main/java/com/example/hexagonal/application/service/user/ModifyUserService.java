package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.ModifyUserCommand;
import com.example.hexagonal.application.port.in.user.ModifyUserUseCase;
import com.example.hexagonal.application.port.out.user.LoadUserPort;
import com.example.hexagonal.application.port.out.user.ModifyUserPort;
import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
class ModifyUserService implements ModifyUserUseCase {

    private final ModifyUserPort modifyUserPort;
    @Override
    public void modifyUser(Long userId, ModifyUserCommand modifyUserCommand) {
        User changeUser = User.withoutId(modifyUserCommand.getMidasUserId(),
                modifyUserCommand.getPassword(),
                modifyUserCommand.getUserName(),
                modifyUserCommand.getTeam(),
                modifyUserCommand.getPhone(),
                modifyUserCommand.getAdminType(),
                stringToRoleType(modifyUserCommand.getUserRoles()));
        modifyUserPort.modifyUser(userId, changeUser);
    }
    public List<RoleType> stringToRoleType(List<String> userRoles) {
        return userRoles.stream()
                .map(userRole -> RoleType.of(userRole))
                .toList();
    }
}
