package com.online.taxi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author WIN10 .
 * @create 2020-07-13-10:55 .
 * @description .
 */

@RestController
public class GrayController {

    private static final Logger log = LoggerFactory.getLogger(GrayController.class);

    @Autowired
    LoadBalancerClient loadBalancerClient;

    /**
     * 开启负载均衡
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @GetMapping("/test/graytest")
    public String grayTest() {

        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance info = loadBalancerClient.choose("graytest");

        String url = "http://" + info.getHost() + ":" + info.getPort() + "/test/graytest";
        log.info("请求url: " + url);

        RestTemplate restTemplate = restTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/test")
    public String test(@RequestHeader("user") String userId) {

        String url = "http://localhost:9000/graytest/test/graytest";
        log.info("请求url: " + url);
        log.info("用户: {}", userId);

        RestTemplate restTemplate = restTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
