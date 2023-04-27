package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetMyListResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService {
    
    @Autowired
    private BoardRepository boardRepository;

    public ResponseDto<List<GetListResponseDto>> getList() {
        List<GetListResponseDto> data = null;

        try{
            List<BoardEntity> boardEntityList = boardRepository.findByOrderByBoardWriteDatetimeDesc();
            data = GetListResponseDto.copyList(boardEntityList);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email) {
        List<GetMyListResponseDto> data = null;

        try {
            List<BoardEntity> boardList = boardRepository.findByWriterEmailOrderByBoardWriteDatetimeDesc(email);
            data = GetMyListResponseDto.copyList(boardList);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    
}   
