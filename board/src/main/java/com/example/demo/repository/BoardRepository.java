package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
    public List<BoardEntity> findByOrderByBoardWriteTimeDesc();

    public List<BoardEntity> findByWriterEmailOrderByBoardWriteTimeDesc(String writerEmail);
}
