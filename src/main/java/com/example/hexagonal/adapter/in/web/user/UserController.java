package com.example.hexagonal.adapter.in.web.user;


import com.example.hexagonal.application.port.in.user.*;
import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.AdminType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관리자 관리 API")
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@RestController
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoadUserUseCase loadUserUseCase;
    private final ModifyUserUseCase modifyUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping("/users")
    public String registerUser(@RequestBody UserRequestDto.Post post) {
        registerUserUseCase.registerUser(RegisterUserCommand.create(
                post.getUserName(),
                post.getMidasUserId(),
                post.getTeam(),
                post.getPassword(),
                post.getPhone(),
                post.getAdminType(),
                post.getUserRoles()));
        return "User Registered.";
    }

    @GetMapping("/users/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId) {
        User user = loadUserUseCase.loadUser(userId);
        return UserResponseDto.toDto(user);
    }

    @GetMapping("/users")
    public Page<UserResponseDto> getUserList(Pageable pageable) {
        Page<User> users = loadUserUseCase.loadUsers(pageable);
        return users.map(user -> UserResponseDto.toDto(user));
    }

    @PutMapping("/users/{userId}")
    public String modifyUser(@PathVariable long userId, @RequestBody UserRequestDto.Put update) {
        modifyUserUseCase.modifyUser(userId, ModifyUserCommand.create(
                update.getUserName(),
                update.getMidasUserId(),
                update.getTeam(),
                update.getPassword(),
                update.getPhone(),
                update.getAdminType(),
                update.getUserRoles()));
        return "User Modified.";
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.deleteUser(userId);
        return "User Deleted.";
    }

}
