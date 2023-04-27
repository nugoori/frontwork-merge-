package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetMyListResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;

public interface BoardService {
    // 게시물 작성 요청과 반환
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto postBoardDto);

    // 작성된 전체적 게시물
    public ResponseDto<List<GetListResponseDto>> getList();

    // 작성자 자신 게시물
    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email);
}
