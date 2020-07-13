package com.online.taxi.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WIN10 .
 * @create 2020-07-09-17:08 .
 * @description .
 */

@RestController
public class TestController {

    @GetMapping()
    public String zuulTest() {
        return "zuulTest";
    }
}
