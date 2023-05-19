package com.example.demo.dto.request.product;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.example.demo.entity.BoardHasProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostBoardHasProductDto {
    @NotBlank
    @Min(1)
    private int boardNumber;

    private int productNumber;

}
