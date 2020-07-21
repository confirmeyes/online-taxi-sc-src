package com.online.taxi.gray;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

/**
 * @author WIN10 .
 * @create 2020-07-21-10:35 .
 * @description .
 */


public class GrayRibbonConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new GrayRule();
    }

}
