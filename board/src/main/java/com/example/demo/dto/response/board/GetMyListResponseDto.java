package com.example.demo.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyListResponseDto {
    private int boardNumber;
    private String boardImgUrl;

    public GetMyListResponseDto(BoardEntity boardEntity) {
        this.boardNumber = boardEntity.getBoardNumber();
        this.boardImgUrl = boardEntity.getBoardImgUrl1();
    }

    public static List<GetMyListResponseDto> copyList(List<BoardEntity> boardEntityList) {

        List<GetMyListResponseDto> list = new ArrayList<>();

        for (BoardEntity boardEntity: boardEntityList) {
            GetMyListResponseDto dto = new GetMyListResponseDto(boardEntity);
            list.add(dto);
        }
        return list;
    }
}
