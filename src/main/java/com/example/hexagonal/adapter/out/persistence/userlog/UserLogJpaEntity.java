package com.example.hexagonal.adapter.out.persistence.userlog;

import com.example.hexagonal.global.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_log")
public class UserLogJpaEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Comment("로그 아이디")
    private long id;

    @Column(name = "midas_user_id", nullable = false, length = 12)
    @Comment("유저 아이디")
    private String midasUserId;

    @Column(name = "ipAddress", nullable = true, length = 20)
    @Comment("아이피 주소")
    private String ipAddress;

    @Column(name = "session_id", nullable = true, length = 50)
    @Comment("세션 아이디")
    private String sessionId;

    @Column(name = "is_login_success", nullable = false, length = 1)
    @Comment("로그인 성공여부")
    private String isLoginSuccess;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "login_datetime", nullable = false, length = 50)
    @Comment("로그인 일자")
    private LocalDateTime loginDatetime;


}
