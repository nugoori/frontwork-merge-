package com.example.demo.service.implementation;

import com.example.demo.dto.request.auth.SignInDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.auth.SignInResponseDto;

public interface AuthService {
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto);
    
}
