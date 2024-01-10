package com.posco.education.domain.dto;

import lombok.Data;

@Data
public class UserJoinRequest {

    private String userId;
    private String password;
    private String username;
    private String department;
}
