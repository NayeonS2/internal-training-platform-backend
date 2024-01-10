package com.posco.education.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quiz_id;     // 아이디
    private String topic;    // 주제
    private String question;     // 퀴즈 질문
    private String answer;     // 퀴즈 답변



}