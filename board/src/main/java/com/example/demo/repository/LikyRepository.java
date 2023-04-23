package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LikyEntity;
import com.example.demo.entity.primaryKey.LikyPK;

@Repository
public interface LikyRepository extends JpaRepository<LikyEntity, LikyPK> {
    
}
