package com.posco.education.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.posco.education.domain.enum_class.UserRole;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "mylecture",
            joinColumns = @JoinColumn(name = "USER"),
            inverseJoinColumns = @JoinColumn(name = "LECTURE"))
    private List<Lecture> lectures = new ArrayList<>();
    @JsonIgnore
    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER")
    private List<Review> reviews = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "POINT")
    private Point point;

//    @Enumerated(EnumType.STRING)
//    private UserRole userRole;      // 권한 등급을 구부



    public void updateUser (Integer quiz_lv, Point point) {

        this.quiz_lv = quiz_lv;
        this.point = point;
    }


}
