package com.posco.education.domain.dto;

import lombok.Data;

@Data
public class UserMyLectureRequest {
    private String userId;
    private Integer lectureId;
}
