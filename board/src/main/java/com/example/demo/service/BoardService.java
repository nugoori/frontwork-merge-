package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetMyLikeListResponseDto;
import com.example.demo.dto.response.board.GetMyListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;

@Service
public interface BoardService {
    // 게시물 작성 요청과 반환
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto);

    // 작성된 전체적 게시물
    public ResponseDto<List<GetListResponseDto>> getList();

    // 작성자 자신 게시물
    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email);

    // 게시물 수정
    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto dto);

    // 작성자가 좋아요를 누른 게시물 가져오기
    public ResponseDto<List<GetMyLikeListResponseDto>> myLikeList(String email);
}
