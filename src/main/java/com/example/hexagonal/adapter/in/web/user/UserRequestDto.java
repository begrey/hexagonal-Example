package com.example.hexagonal.adapter.in.web.user;

import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;


@Schema(description = "관리자 관리 요청 객체")
class UserRequestDto {

//    @Builder
    @Getter
    public static class Post {
        private String userName;
        private String midasUserId;
        private String team;
        private String password;
        private String phone;
        private AdminType adminType;
        List<RoleType> userRoles; // 차후에 ENUM을 통해 role 관리
    }

    @Getter
    public static class Put {
        private String userName;
        private String midasUserId;
        private String team;
        private String password;
        private String phone;
        private AdminType adminType;
        List<RoleType> userRoles; // 차후에 ENUM을 통해 role 관리

    }

}
