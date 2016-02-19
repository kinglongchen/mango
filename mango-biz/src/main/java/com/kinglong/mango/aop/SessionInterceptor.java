package com.kinglong.mango.aop;


import com.kinglong.mango.domian.SessionContext;
import com.kinglong.mango.manager.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by chenjinlong on 16/2/5.
 */
@Slf4j
@Component
public class SessionInterceptor implements MethodInterceptor {
    @Resource
    SessionManager sessionManager;

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("sessionId")) {
                sessionId = cookie.getValue();
            }
        }
        SessionContext sessionContext = sessionManager.getSession(sessionId);
        if (sessionContext == null) {
            return "login";
        }
        return methodInvocation.proceed();
    }


    private static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }
}
