package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LikyEntity;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, String> {
    
}
