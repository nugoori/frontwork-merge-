package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetMyListResponseDto;

public interface BoardService {
    public ResponseDto<List<GetListResponseDto>> getList();

    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email);
}
