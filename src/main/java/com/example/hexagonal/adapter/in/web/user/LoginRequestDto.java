package com.example.hexagonal.adapter.in.web.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 요청 객체")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class LoginRequestDto {

    private String userId;
    private String password;
}
