package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

    @Autowired
    private BoardService boardService;

    private final String GET_LIST = "/list";
    private final String GET_MY_LIST ="/my-list";
    
    @GetMapping(GET_LIST)
    public ResponseDto<List<GetListResponseDto>> getList() {
        ResponseDto<List<GetListResponseDto>> response = boardService.getList();
        return response;
    }
}
