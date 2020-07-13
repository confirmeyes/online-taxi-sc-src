package com.online.taxi.filter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WIN10 .
 * @create 2020-07-08-16:40 .
 * @description 灰度发布在路由过滤器中分发.
 */

@Component
public class GrayFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(GrayFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        //获取token中用户信息
        //对用户信息和数据库或缓存中的灰度规则进行比较
        //筛选后进行分发路由
        log.info("用户: {}", request.getHeader("xxxx"));
        int userId = Integer.parseInt(request.getHeader("xxxx"));
        if (userId == 1) {
            //ribbon-discovery-filter 进行分发路由
            RibbonFilterContextHolder.getCurrentContext().add("version", "v1");
            // 普通用户
        } else if (userId == 2) {
            RibbonFilterContextHolder.getCurrentContext().add("version", "v2");
        }
        return null;
    }
}
