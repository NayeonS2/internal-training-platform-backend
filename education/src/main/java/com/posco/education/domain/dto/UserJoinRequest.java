package com.posco.education.domain.dto;

import lombok.Data;

@Data
public class UserJoinRequest {

    private String loginId;
    private String password;
    private String passwordCheck;
    private String nickname;
}
