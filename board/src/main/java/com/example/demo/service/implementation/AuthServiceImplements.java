package com.example.demo.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.auth.SignInDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.auth.SignInResponseDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.provider.TokenProvider;
import com.example.demo.repository.UserRepository;

@Service
public class AuthServiceImplements implements AuthService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {

        SignInResponseDto data = null;

        String email = dto.getEmail();
        String password = dto.getPassword();

        UserEntity userEntity = null;

        try{
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null)
                return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);

            boolean isEqualPassword = passwordEncoder.matches(password, userEntity.getPassword());    
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        try {
            String token = tokenProvider.create(email);
            data = new SignInResponseDto(userEntity, token);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.FAIL_SIGN_IN);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
