package com.posco.education.controller;

import com.posco.education.domain.dto.TokenDto;
import com.posco.education.domain.dto.UserLoginRequest;
import com.posco.education.domain.dto.UserMyLectureRequest;
import com.posco.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserLoginRequest userLoginRequest) {
        String memberId = userLoginRequest.getLoginId();
        String password = userLoginRequest.getPassword();
        TokenDto tokenDto = userService.login(memberId, password);
        return tokenDto;
    }

}
