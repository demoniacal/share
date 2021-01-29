package com.example.share.rest;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCheckController {

    public String redisTest() {
        return "hello";
    }
}
