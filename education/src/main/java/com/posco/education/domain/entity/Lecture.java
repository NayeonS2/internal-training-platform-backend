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
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lecture_id;     // 아이디
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;     // 유저
    private String topic;    // 주제
    private String title;    // 강의명
    @Column(columnDefinition = "text")
    private String description;     // 강의 설명
    private String video_link;    // 강의 링크
    private Integer like_cnt;     // 강의 찜 수

}