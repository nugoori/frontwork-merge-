package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Liky")
@Table(name = "Liky")
public class LikyEntity {
    @Id
    private int boardNumber;
    @Id
    private String userEmail;
    private String UserNickname;
    private String userProfileUrl;
}
