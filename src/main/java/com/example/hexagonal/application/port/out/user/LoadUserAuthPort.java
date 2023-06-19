package com.example.hexagonal.application.port.out.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface LoadUserAuthPort {
    List<String> getUserRoles(String token);
    UserDetails getUserDetailByUserName(String midasUserId);
}
