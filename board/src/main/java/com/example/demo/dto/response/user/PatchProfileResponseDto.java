package com.example.demo.dto.response.user;

import com.example.demo.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchProfileResponseDto {
    private String profile;

    public PatchProfileResponseDto(UserEntity userEntity) {
        this.profile = userEntity.getProfile();
    }
}
