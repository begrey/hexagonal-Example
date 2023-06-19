package com.example.hexagonal.domain.user;

import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.enums.RoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private final Long userId;

    private final String midasUserId;

    private final String password;

    private final String userName;

    private final String team;

    private final String phone;

    private final AdminType adminType;

    private final List<RoleType> userRoles;


    public static User withId(Long userId,
                           String midasUserId,
                           String password,
                           String userName,
                           String team,
                           String phone,
                           AdminType adminType,
                           List<RoleType> userRoles) {
        return new User(userId, midasUserId, password, userName, team, phone, adminType, userRoles);
    }
    public static User withoutId(String midasUserId,
                           String password,
                           String userName,
                           String team,
                           String phone,
                           AdminType adminType,
                           List<RoleType> userRoles) {
        return new User(null, midasUserId, password, userName, team, phone, adminType, userRoles);
    }

}
