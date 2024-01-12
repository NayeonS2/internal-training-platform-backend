package com.posco.education.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;
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
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;



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
