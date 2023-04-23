package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Board")
@Table(name="Board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AOTO_INCREMENT 의 기능
    private int boardNumber;
    private String boardContent;
    private String boardImgUrl1;
    private String boardImgUrl2;
    private String boardImgUrl3;
    private String tag;
    private String boardWriterDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileUrl;
    private String commentCount;
    private String likeCount;
    private String viewCount;
}
