package com.example.hexagonal.application.port.out.user;

import com.example.hexagonal.global.enums.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface LoadUserAuthPort {
    List<RoleType> getUserRoles(String userName, String password);
    UserDetails getUserDetailByUserName(String midasUserId);

}
