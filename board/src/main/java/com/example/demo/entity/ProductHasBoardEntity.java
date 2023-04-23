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
@Entity(name = "ProductHasBaord")
@Table(name = "ProductHasBoard")
public class ProductHasBoardEntity {

    @Id
    private int productNumber;
    @Id
    private int boardNumber;
}
