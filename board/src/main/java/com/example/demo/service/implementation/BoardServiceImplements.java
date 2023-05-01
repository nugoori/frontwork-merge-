package com.example.demo.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.constant.ResponseMessage;
import com.example.demo.dto.request.board.PatchBoardDto;
import com.example.demo.dto.request.board.PostBoardDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.GetListResponseDto;
import com.example.demo.dto.response.board.GetMyListResponseDto;
import com.example.demo.dto.response.board.PatchBoardResponseDto;
import com.example.demo.dto.response.board.PostBoardResponseDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.BoardHasProductRepository;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LikyRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BoardService;

@Service
public class BoardServiceImplements implements BoardService {
    
    @Autowired UserRepository userRepository;
    @Autowired BoardRepository boardRepository;
    @Autowired ProductRepository productRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired LikyRepository likyRepository;
    @Autowired BoardHasProductRepository boardHasProductRepository;

    // 게시물 작성하기
    public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto) {
        PostBoardResponseDto data = null;

        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
            
            BoardEntity boardEntity = new BoardEntity(userEntity, dto);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();

            List<CommentEntity> commentEntity = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<LikyEntity> likyEntity = likyRepository.findByBoardNumber(boardNumber);

            // List<ProductEntity> productEntityList = null;
            
            // List<BoardHasProductEntity> boardHasProductEntity = boardHasProductRepository.findByBoardNumber(boardNumber);
            // for (BoardHasProductEntity number : boardHasProductEntity) {
            //     int productNumber = number.getBoardHasProductPk().getProductNumber();
            //     ProductEntity productEntity = productRepository.findById(productNumber) ;
            //     productEntityList.add(productEntity);
            // }

            data = new PostBoardResponseDto(boardEntity, commentEntity, likyEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 게시물 리스트 가져오기
    public ResponseDto<List<GetListResponseDto>> getList() {
        List<GetListResponseDto> data = null;

        try{
            List<BoardEntity> boardEntityList = boardRepository.findByOrderByBoardWriteTimeDesc();
            data = GetListResponseDto.copyList(boardEntityList);

        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 자신이 작성한 게시물 가져오기
    public ResponseDto<List<GetMyListResponseDto>> getMyList(String email) {
        List<GetMyListResponseDto> data = null;

        try {
            List<BoardEntity> boardList = boardRepository.findByWriterEmailOrderByBoardWriteTimeDesc(email);
            data = GetMyListResponseDto.copyList(boardList);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    // 작성된 계시물 수정하기
    public ResponseDto<PatchBoardResponseDto> patchBoard(String email, PatchBoardDto dto) {
        PatchBoardResponseDto data = null;

        int boardNumber = dto.getBoardNumber();
        
        try {
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);
            
            boolean isMatch = email.equals(boardEntity.getWriterEmail());
            if (!isMatch) return ResponseDto.setFailed(ResponseMessage.NOT_PERMISSION);

            List<CommentEntity> commentEntity = commentRepository.findByBoardNumberOrderByWriterDateDesc(boardNumber);
            List<LikyEntity> likyEntity = likyRepository.findByBoardNumber(boardNumber);

            boardEntity.patch(dto);
            boardRepository.save(boardEntity);

            data = new PatchBoardResponseDto(boardEntity, commentEntity, likyEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    
}   
