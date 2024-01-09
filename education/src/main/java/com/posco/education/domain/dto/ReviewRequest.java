package com.posco.education.domain.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private String userId;
    private Integer lectureId;
    private Integer reviewId;
    private String title;
    private String content;
}
