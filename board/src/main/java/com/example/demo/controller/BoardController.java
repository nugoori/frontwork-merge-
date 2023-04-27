package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetMyListResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired
    private BoardService boardService;

    private final String POST_BOARD = "/post-board";

    private final String GET_LIST = "/list";
    private final String GET_MY_LIST ="/my-list";
    
    @PostMapping(POST_BOARD)
    public ResponseDto<PostBoardResponseDto> postBoard(@AuthenticationPrincipal String email, @RequestBody PostBoardDto requestBody) {
        ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);
        return response;
    }

    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }

    @GetMapping(GET_MY_LIST)
    public ResponseDto<List<GetMyListResponseDto>> getMyList(
        @AuthenticationPrincipal String email
    ) {
        ResponseDto<List<GetMyListResponseDto>> response = boardService.getMyList(email);
        return response;
    }
}
