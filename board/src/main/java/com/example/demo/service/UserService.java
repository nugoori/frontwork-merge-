package com.example.demo.service;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.GetUserResponseDto;

public interface UserService {
    public ResponseDto<GetUserResponseDto> getUser(String email);
}
