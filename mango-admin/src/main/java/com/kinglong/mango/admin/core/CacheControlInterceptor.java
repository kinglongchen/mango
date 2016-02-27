package com.kinglong.mango.admin.core;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cacheControl控制器
 */
@Component
public class CacheControlInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) return true;
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        CacheControl annotation = method.getAnnotation(CacheControl.class);
//        if (null == annotation) return true;
//        long maxTime = annotation.unit().toSeconds(annotation.time());
//        response.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=" + maxTime);
//        return true;
        return true;
    }
}
