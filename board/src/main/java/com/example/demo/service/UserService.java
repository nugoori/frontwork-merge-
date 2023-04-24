package com.example.demo.service;

import com.example.demo.dto.response.GetUserResponseDto;
import com.example.demo.dto.response.ResponseDto;

public interface UserService {
    public ResponseDto<GetUserResponseDto> getUser(String email);
}
