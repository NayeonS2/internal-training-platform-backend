package com.posco.education.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PointAddResponse {
    private Integer language_p;     // 언어 포인트
    private Integer production_p;     // 생산 포인트
    private Integer finance_p;     // 재무 포인트
    private Integer marketing_p;     // 마케팅 포인트
    private Integer it_p;     // it 포인트

    private Integer sum_p;     // 총 포인트

    private String user;
    private Integer user_lv;
    private Integer is_lvup;
}
