package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;

public interface BoardService {
    public ResponseDto<List<GetListResponseDto>> getList();
}
