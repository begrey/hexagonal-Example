package com.example.hexagonal.adapter.in.web.user;

import com.example.hexagonal.domain.user.User;
import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;


@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "관리자 관리 응답 객체")
class UserResponseDto{
    private Long userId;
    private String userName;
    private String midasUserId;
    private String team;
//    private String password;
    private String phone;
    private AdminType adminType;
    List<RoleType> userRoles;


    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getUserId(),
                user.getUserName(),
                user.getMidasUserId(),
                user.getTeam(),
                user.getPhone(),
                user.getAdminType(),
                user.getUserRoles());
    }

}

