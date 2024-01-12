package com.posco.education.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Integer lectureId;     // 아이디
    private String topic;    // 주제
    private String title;    // 강의명
    @Column(columnDefinition = "text")
    private String description;     // 강의 설명
    private String video_link;    // 강의 링크
    private String thumbnail_url;    // 강의 링크
    private String channel_name;    // 강의 링크
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date published_date;    // 강의 링크
    @Column(name = "like_cnt")
    private Integer likeCnt;     // 강의 찜 수
    @JsonIgnore
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "LECTURE")
    private List<Review> reviews = new ArrayList<Review>();




    public void updateLecture (Integer like_cnt) {

        this.likeCnt = like_cnt;
    }
}