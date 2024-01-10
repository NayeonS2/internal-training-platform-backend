package com.posco.education.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.posco.education.domain.enum_class.UserRole;
import javax.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User implements UserDetails {
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

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void updateUser (Integer quiz_lv, Point point) {

        this.quiz_lv = quiz_lv;
        this.point = point;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return user_id;
    }

    @Override
    public String getPassword() {
        return user_pw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
