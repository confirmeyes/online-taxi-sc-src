package com.online.taxi.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WIN10 .
 * @create 2020-07-21-11:55 .
 * @description .
 */

@RestController
public class LimitController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/limitTest")
    public String grayTest() {
        return "limit-test:" + port;
    }
}
