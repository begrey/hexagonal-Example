package com.example.hexagonal.adapter.out.persistence.user;

import com.example.hexagonal.application.port.out.user.*;
import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Transactional
class UserPersistenceAdapter implements RegisterUserPort, LoadUserPort, ModifyUserPort, DeleteUserPort {
    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final UserMapper userMapper;


    @Override
    public void registerUser(User user) {
        UserJpaEntity jpaEntity = userRepository.save(userMapper.toUserJpaEntity(user));
        List<UserRoleJpaEntity> userRoleJpaEntities = setUserRole(user, jpaEntity);
        jpaEntity.setUserRoles(userRoleJpaEntities);
    }

    public List<UserRoleJpaEntity> setUserRole(User user, UserJpaEntity userJpaEntity) {
        List<UserRoleJpaEntity> userRoleEntities = new ArrayList<>();
        for (RoleType userRole : user.getUserRoles()) {
            UserRoleJpaEntity role = userRoleRepository.save(userMapper.toRoleJpaEntity(userRole, userJpaEntity));
            userRoleEntities.add(role);
        }
        return userRoleEntities;
    }

    @Override
    public User readUserByMidasUserIdAndPassword(String midasUserId, String password) {
        UserJpaEntity userJpaEntity = Optional.ofNullable(userRepository.findByMidasUserIdAndPassword(midasUserId,password))
                .orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않음"));
        return userMapper.toDomain(userJpaEntity);
    }

    @Override
    public User readUserByUserId(Long userId) {
        UserJpaEntity userJpaEntity = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않음"));
        return userMapper.toDomain(userJpaEntity);
    }

    @Override
    public Page<User> readAllUser(Pageable pageable) {
        Page<UserJpaEntity> userJpaEntities = userRepository.findAll(pageable);
        return userJpaEntities.map(userEntity -> userMapper.toDomain(userEntity));
    }

    @Override
    public void modifyUser(Long userId, User changeUser) {
        UserJpaEntity userJpaEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 User가 존재하지 않음"));

        // 기존 userRole 제거
        deleteUserRole(userJpaEntity.getUserId());
        userJpaEntity.update(changeUser);
        UserJpaEntity changeUserEntity = userRepository.save(userJpaEntity);
        // 새로운 userRole저장
        List<UserRoleJpaEntity> userRoleJpaEntities = setUserRole(changeUser, userJpaEntity);
        changeUserEntity.setUserRoles(userRoleJpaEntities);
    }

    @Override
    public void deleteUser(Long userId) {
        deleteUserRole(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteUserRole(Long userId) {
        userRoleRepository.deleteAllByUserJpaEntityUserId(userId);
    }


}
