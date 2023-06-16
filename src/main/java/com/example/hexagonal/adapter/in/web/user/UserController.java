package com.example.hexagonal.adapter.in.web.user;


import com.example.hexagonal.application.port.in.user.*;
import com.example.hexagonal.global.enums.AdminType;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "관리자 관리 API")
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@RestController
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    @PostMapping("/users")
    public void registerUser(@RequestBody UserRequestDto.Post post) {
        registerUserUseCase.registerUser(RegisterUserCommand.create(
                post.getUserName(),
                post.getMidasUserId(),
                post.getTeam(),
                post.getPassword(),
                post.getPhone(),
                post.getAdminType(),
                post.getUserRoles()));
    }

//    @GetMapping("/users/{userId}")
//    public UserResponseDto getUser(@PathVariable Long userId) {
//        return userService.selectUser(userId);
//    }
//
//    @GetMapping("/users")
//    public List<UserResponseDto> getUserList(Pageable pageable) {
//        return userService.selectUserList(pageable);
//    }
//
//    @PutMapping("/users/{userId}")
//    public UserResponseDto modifyUser(@PathVariable long userId, @RequestBody UserRequestDto.Put update) {
//        return userService.modifyUser(userId, update);
//    }
//
//    @DeleteMapping("/users/{userId}")
//    public void deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//    }

}
