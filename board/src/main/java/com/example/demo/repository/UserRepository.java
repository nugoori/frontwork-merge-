package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEntity;

@Repository //지정된 인터페이스를 리포지토리로 설정 하겠다.
public interface UserRepository extends JpaRepository<UserEntity, String> { //JpaRepository를 상속받음. 제네릭엔 엔티티, id의 타입을 기입
    
}
