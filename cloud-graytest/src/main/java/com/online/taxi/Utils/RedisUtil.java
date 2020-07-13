package com.online.taxi.Utils;

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
    private static RedisTemplate<String, Object> redisTemplate;

    private static final int EXPIRATION = 3;


    public static void setVaule(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
        redisTemplate.expire(key, EXPIRATION, TimeUnit.HOURS);
    }


    public static String getVaule(String key, Object val) {
        return String.valueOf(redisTemplate.opsForValue().get(key));
    }
}
