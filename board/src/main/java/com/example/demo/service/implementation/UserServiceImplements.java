package com.example.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.user.GetUserResponseDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.common.constant.ResponseMessage;

@Service //서비스로 지정
public class UserServiceImplements implements UserService {
    
    @Autowired private UserRepository userRepository;

    public ResponseDto<GetUserResponseDto> getUser(String email) {
        GetUserResponseDto data = null;

        // 찾는 값이 있으면 성공 메세지를 반환, 데이터 베이스가 오류거나, 찾는 값이 없으면 에러메세지 반환.
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null)
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            data = new GetUserResponseDto(userEntity);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
