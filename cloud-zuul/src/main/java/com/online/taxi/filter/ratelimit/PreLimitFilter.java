package com.online.taxi.filter.ratelimit;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author WIN10 .
 * @create 2020-07-21-12:01 .
 * @description .
 */

@Component
public class PreLimitFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(1);

    @Override
    public Object run() throws ZuulException {
        System.out.println("PreLimitFilter!!!!!!!!!!!!");
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.set("rateLimit", true);
        if (RATE_LIMITER.tryAcquire()) {
            return null;
        } else {
            currentContext.set("rateLimit", false);
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}
