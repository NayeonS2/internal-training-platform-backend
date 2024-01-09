package com.posco.education.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer point_id;     // 아이디
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;     // 유저
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;     // 퀴즈
    private Integer amount;     // 포인트 점수

}