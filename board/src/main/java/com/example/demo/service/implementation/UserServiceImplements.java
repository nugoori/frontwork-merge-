package com.example.demo.service.implementation;

import org.springframework.stereotype.Service;

import com.example.demo.dto.response.GetUserResponseDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.common.constant.ResponseMessage;

@Service
public class UserServiceImplements implements UserService {
    private UserRepository userRepository;

    public ResponseDto<GetUserResponseDto> getUser(String email) {
        GetUserResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null)
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
