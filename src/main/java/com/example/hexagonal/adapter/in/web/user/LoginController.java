package com.example.hexagonal.adapter.in.web.user;

import com.example.hexagonal.application.port.in.user.LoginCommand;
import com.example.hexagonal.application.port.in.user.LoginUseCase;
import com.example.hexagonal.application.port.in.user.LogoutUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Tag(name = "로그인/로그아웃 API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
class LoginController {
    private final LoginUseCase loginUseCase;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto userLoginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginCommand loginCommand = new LoginCommand(userLoginDto.getUserId(),
                                                    userLoginDto.getPassword(),
                                                    request,
                                                    response);
        return loginUseCase.login(loginCommand);
    }
}
