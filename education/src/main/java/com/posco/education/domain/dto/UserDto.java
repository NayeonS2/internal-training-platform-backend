package com.posco.education.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private String loginId;
    private String nickname;
    private String nowPassword;
    private String newPassword;
    private String newPasswordCheck;

}
