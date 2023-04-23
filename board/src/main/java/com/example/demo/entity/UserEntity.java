package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //전체 생성자
@Entity(name="User") //설정할 엔티티의 명
@Table(name="User") //데이터 베이스 테이블 명
public class UserEntity {
    @Id //PRIMARY KEY
    private String email;
    private String password;
    private String nickname;
    private String profile;
    private String height;
    private String weight;
    private String gender;
}
