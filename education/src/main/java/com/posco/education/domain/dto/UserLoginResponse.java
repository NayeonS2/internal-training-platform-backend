package com.posco.education.domain.dto;

import lombok.Data;

@Data
public class UserLoginResponse {
    TokenDto tokenDto;
    Integer totalPoint;
}
