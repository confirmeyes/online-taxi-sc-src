package com.online.taxi.controller;

import com.online.taxi.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WIN10 .
 * @create 2020-07-09-15:15 .
 * @description .
 */

@RestController
@RequestMapping("/test")
public class GrayTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/graytest")
    public String grayTest() {
        return "gray-test:" + port;
    }


    @GetMapping("/grayRedisTest")
    public String grayRedisTest() {

        RedisUtil.setVaule("v1", 1);
        RedisUtil.setVaule("v2", 2);
        return "grayRedisTest";
    }
}
