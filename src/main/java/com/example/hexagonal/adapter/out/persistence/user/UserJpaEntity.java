package com.example.hexagonal.adapter.out.persistence.user;

import com.example.hexagonal.global.enums.AdminType;
import com.example.hexagonal.global.model.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name="user",
        uniqueConstraints = {@UniqueConstraint(name="uk_user_001", columnNames = "phone"),
        })
public class UserJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Comment("유저 아이디")
    private Long userId;

    @Column(name = "midas_user_id", nullable = false, length = 12)
    @Comment("유저 가입 아이디")
    private String midasUserId;

    @Column(name = "password", nullable = false, length = 12)
    @Comment("비밀번호")
    private String password;

    @Column(name = "user_name", nullable = false, length = 12)
    @Comment("성명")
    private String userName;

    @Column(name = "team", length = 12)
    @Comment("소속")
    private String team;

    @Column(name = "phone", length = 12)
    @Comment("연락처")
    private String phone;

    @Column(name = "admin_type", length = 20)
    @Comment("권한")
    @Enumerated(EnumType.STRING)
    private AdminType adminType;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Column
    private List<UserRoleJpaEntity> userRoles = new ArrayList<>();

    public void setUserRoles (List<UserRoleJpaEntity> userRoles) {
        this.userRoles = userRoles;
    }


//    public UserJpaEntity update(UserRequestDto.Put update) {
//        this.team = update.getTeam();
//        this.phone = update.getPhone();
//        this.adminType = update.getAdminType();
//        this.userName = update.getUserName();
//        return this;
//    }


}
