package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoardHasProductEntity;
import com.example.demo.entity.primaryKey.BoardHasProductPK;

public interface BoardHasProductRepository extends JpaRepository<BoardHasProductEntity, BoardHasProductPK>{
    
}
