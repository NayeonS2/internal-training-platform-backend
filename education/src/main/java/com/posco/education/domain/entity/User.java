package com.posco.education.domain.entity;

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
    private String user_id;     // 아이디

    private String user_pw;     // 비밀번호
    private String user_name;    // 유저 이름
    private String department;    // 부서명
    private Integer quiz_lv;    // 퀴즈 레벨
}
