package com.example.hexagonal.adapter.in.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "관리자 관리 응답 객체")
class UserResponseDto{
    private Long userId;
    private String userName;
    private String midasUserId;
    private String team;
    private String password;
    private String phone;
    private String role;
    List<String> userRoles; // 반환값은 url값만 , 차후에 ENUM을 통해 role 관리


//    public static UserResponseDto toDto(User user, List<UserRole> userRoles, ModelMapper modelMapper) {
//        UserResponseDto dto = modelMapper.map(user, UserResponseDto.class);
//        dto.setUserRoleUrl(userRoles);
//        return dto;
//    }
//
//    public void setUserRoleUrl(List<UserRole> userRoles) {
//        this.userRoles = userRoles.stream().map(role -> role.getUrl()).toList();
//    }

}

