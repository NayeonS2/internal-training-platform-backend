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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;     // 아이디
    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;     // 강의
    private String title;    // 리뷰 제목
    @Column(columnDefinition = "text")
    private String content;     // 리뷰 내용
    private Integer like_cnt;     // 리뷰 좋아요 수

}