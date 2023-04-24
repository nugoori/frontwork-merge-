package com.example.demo.controller;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.response.GetUserResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    @Autowired
    private UserService userService;
    
    private final String GET_USER = "/";
    private final String PATCH_PROFILE = "/profile";

    @GetMapping(GET_USER)
    public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String email) {
        ResponseDto<GetUserResponseDto> response = userService.getUser(email);
        return response;
    }
}
