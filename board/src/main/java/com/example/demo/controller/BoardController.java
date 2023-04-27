package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.constant.ApiPattern;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {
    private final String GET_LIST = "/list";
    private final String GET_MY_LIST ="/my-list";

    
}
