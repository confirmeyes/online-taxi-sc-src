package com.online.taxi.gray;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WIN10 .
 * @create 2020-07-17-18:05 .
 * @description .
 */

@Aspect
@Component
public class RequestAspect {

    //com.online.taxi.controller.GrayController

    @Pointcut("execution(* com.online.taxi.controller..*Controller*.*(..))")
    private void cutMethod() {

    }

    @Before(value = "cutMethod()")
    public void before(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String version = request.getHeader("version");

        Map<String, String> map = new HashMap<>();
        map.put("version", version);
        //利用 ThreadLocal 对 Version进行保存，在GrayRule中进行校验
        RibbonParameters.set(map);
    }

}
