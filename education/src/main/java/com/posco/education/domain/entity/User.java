package com.posco.education.domain.entity;

import com.posco.education.domain.enum_class.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String loginId;     // 로그인할 때 사용하는 아이디
    private String password;    // 비밀번호
    private String nickname;    // 닉네임
    private LocalDateTime createdAt;    // 가입 시간
    private Integer receivedLikeCnt; // 유저가 받은 좋아요 개수 (본인 제외)

    @Enumerated(EnumType.STRING)
    private UserRole userRole;      // 권한 등급을 구부

}
