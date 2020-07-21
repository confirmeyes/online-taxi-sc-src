package com.online.taxi.filter.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author WIN10 .
 * @create 2020-07-21-12:01 .
 * @description .
 */

@Component
public class LimitFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -9;
    }

    @Override
    public boolean shouldFilter() {
        return (boolean) RequestContext.getCurrentContext().get("rateLimit");
    }

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public Object run() throws ZuulException {

        System.out.println("LimitFilter!!!!!!!!!!!!");

        return null;
    }
}
