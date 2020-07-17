package com.online.taxi.gray;

import org.springframework.stereotype.Component;

/**
 * @author WIN10 .
 * @create 2020-07-17-17:55 .
 * @description .
 */

@Component
public class RibbonParameters {

    private static final ThreadLocal LOCAL = new ThreadLocal();

    static <T> T get() {
        return (T) LOCAL.get();
    }

    static <T> void set(T t) {
        LOCAL.set(t);
    }

}
