package com.example.hexagonal.application.service.user;

import com.example.hexagonal.application.port.in.user.AddUserLogUseCase;
import com.example.hexagonal.application.port.in.user.LoginCommand;
import com.example.hexagonal.application.port.in.user.LoginUseCase;
import com.example.hexagonal.application.port.in.user.LogoutUseCase;
import com.example.hexagonal.application.port.out.user.LoadUserPort;
import com.example.hexagonal.application.port.out.userlog.AddUserLogPort;
import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.domain.userlog.UserLog;
import com.example.hexagonal.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
class LoginService implements LoginUseCase, AddUserLogUseCase {

    private final LoadUserPort loadUserPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final AddUserLogPort addUserLogPort;

    @Override
    public String login(LoginCommand loginCommand) {
        boolean loginSuccess = true;
        String token = null;
        try {
            User user = loadUserPort.readUserByMidasUserIdAndPassword(loginCommand.getMidasUserId(), loginCommand.getPassword());
            token = jwtTokenProvider.generateToken(user.getMidasUserId(), user.getPassword(), user.getAdminType().getRole());
        } catch (Exception e) {
            loginSuccess = false;
        }
        addUserLog(loginCommand.getMidasUserId(), loginCommand.getRequest(), loginSuccess);
        return token;

    }


    @Override
    public void addUserLog(String midasUserId, HttpServletRequest request, boolean loginSuccess) {
        String sessionId = null;
        if (request.getCookies() != null) {
            sessionId = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie != null && cookie.getName().equals("JSESSIONID"))
                    .map(cookie -> cookie.getValue())
                    .findFirst().orElse(null);
        }
        String ipAddress = request.getRemoteAddr();
        UserLog userLog = UserLog.withoutId(midasUserId, ipAddress, sessionId, loginSuccess);
        addUserLogPort.AddUserLog(userLog);
    }
}
