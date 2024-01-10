package com.posco.education.controller;

import com.posco.education.domain.dto.TokenDto;
import com.posco.education.domain.dto.UserJoinRequest;
import com.posco.education.domain.dto.UserLoginRequest;
import com.posco.education.domain.dto.UserMyLectureRequest;
import com.posco.education.domain.entity.User;
import com.posco.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @PostMapping("/save")
    public String registry(@RequestBody UserJoinRequest userJoinRequest) {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");

        User user = User.builder()
                .userId(userJoinRequest.getUserId())
                .user_name(userJoinRequest.getUsername())
                .password(userJoinRequest.getPassword())
                .department(userJoinRequest.getDepartment())
                .roles(roles)
                .build();
        String userId = userService.join(user);

        return userId;
    }

    @PostMapping("/test")
    public String test() {
        return "sucess";
    }
}
