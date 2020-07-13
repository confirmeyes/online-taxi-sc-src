package com.online.taxi.filter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.val;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WIN10 .
 * @create 2020-07-09-15:28 .
 * @description .
 */
public class zuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        int userId = Integer.parseInt(request.getHeader("userId"));
        if (userId == 1) {
            RibbonFilterContextHolder.getCurrentContext().add("version", "v1");
            // 普通用户
        } else if (userId == 2) {
            RibbonFilterContextHolder.getCurrentContext().add("version", "v2");
        }


        return null;
    }
}
