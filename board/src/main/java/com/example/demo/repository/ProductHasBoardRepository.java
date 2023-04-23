package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductHasBoardEntity;
import com.example.demo.entity.primaryKey.ProductHasBoardPK;

public interface ProductHasBoardRepository extends JpaRepository<ProductHasBoardEntity, ProductHasBoardPK>{
    
}
