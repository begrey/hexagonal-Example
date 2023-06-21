package com.example.hexagonal.application.port.in.user;

import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.enums.RoleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ModifyUserCommand {
    @NotBlank
    private String userName;
    @NotBlank
    private String midasUserId;
    private String team;
    @NotBlank
    private String password;
    private String phone;
    @NotBlank
    private AdminType adminType;
    List<RoleType> userRoles;

    public static ModifyUserCommand create(String userName,
                                             String midasUserId,
                                             String team,
                                             String password,
                                             String phone,
                                             AdminType adminType,
                                             List<RoleType> userRoles) {
        return new ModifyUserCommand(userName, midasUserId, team, password, phone, adminType, userRoles);
    }
}
