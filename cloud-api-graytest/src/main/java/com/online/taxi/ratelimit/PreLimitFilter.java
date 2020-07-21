package com.online.taxi.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author WIN10 .
 * @create 2020-07-21-10:59 .
 * @description 服务使用Filter实现限流.
 */

@Component
public class PreLimitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        RateLimiter rateLimiter = RateLimiter.create(2);

        if (rateLimiter.tryAcquire()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("限流!");
        }
    }

    @Override
    public void destroy() {
    }
}
