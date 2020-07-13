package com.online.taxi.filter.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author WIN10 .
 * @create 2020-07-09-15:38 .
 * @description .
 */

public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final int EXPIRATION = 3;


    public void setVaule(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
        redisTemplate.expire(key, EXPIRATION, TimeUnit.HOURS);
    }


    public String getVaule(String key, Object val) {
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }
}
