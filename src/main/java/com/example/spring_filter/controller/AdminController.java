package com.example.spring_filter.controller;

import com.example.spring_filter.constant.APIUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIUrl.ADMIN)
public class AdminController {

//    @GetMapping(produces = "application/json")
//    public String hello() {
//        return "{\"message\": \"Hello, World!\"}";
//    }
}
