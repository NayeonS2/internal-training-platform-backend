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

    private Integer language_p;     // 언어 포인트
    private Integer production_p;     // 생산 포인트
    private Integer finance_p;     // 재무 포인트
    private Integer marketing_p;     // 마케팅 포인트
    private Integer it_p;     // it 포인트

}