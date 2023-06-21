package com.example.hexagonal.adapter.out.persistence.user;

import com.example.hexagonal.application.port.in.user.RegisterUserCommand;
import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class UserMapper {
    User toDomain(UserJpaEntity userJpaEntity) {
        List<RoleType> roleTypes = userJpaEntity.getUserRoles().stream()
                .map(userRole -> userRole.getUrl())
                .toList();
        return User.withId(userJpaEntity.getUserId(),
                userJpaEntity.getMidasUserId(),
                userJpaEntity.getPassword(),
                userJpaEntity.getUserName(),
                userJpaEntity.getTeam(),
                userJpaEntity.getPhone(),
                userJpaEntity.getAdminType(),
                roleTypes);
    }

    UserJpaEntity toUserJpaEntity(User user) {
        return UserJpaEntity.builder()
                .midasUserId(user.getMidasUserId())
                .password(user.getPassword())
                .userName(user.getUserName())
                .team(user.getTeam())
                .phone(user.getPhone())
                .adminType(user.getAdminType())
                .build();

    }

    UserRoleJpaEntity toRoleJpaEntity(RoleType roleType, UserJpaEntity user) {
                return UserRoleJpaEntity.builder()
                    .url(roleType).userJpaEntity(user).build();
    }
}
