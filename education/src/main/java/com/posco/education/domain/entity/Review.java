package com.posco.education.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer review_id;     // 아이디

    @ManyToOne
    @JoinColumn(name = "USER", insertable = false, updatable = false)
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "LECTURE", insertable = false, updatable = false)
    private Lecture lecture;     // 강의
    private String title;    // 리뷰 제목
    @Column(columnDefinition = "text")
    private String content;     // 리뷰 내용
    @Column(name = "like_cnt")
    private Integer likeCnt;     // 리뷰 좋아요 수



    public void updateReview (User user, Lecture lecture, String title, String content, Integer like_cnt) {
        this.user = user;
        this.lecture = lecture;
        this.title = title;
        this.content = content;
        this.likeCnt = like_cnt;
    }

    public void deleteReview (User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;

    }

}