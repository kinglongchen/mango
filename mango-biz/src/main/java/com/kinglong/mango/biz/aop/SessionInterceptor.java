package com.kinglong.mango.biz.aop;


import com.kinglong.mango.biz.domian.SessionContext;
import com.kinglong.mango.biz.manager.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * Created by chenjinlong on 16/2/5.
 */
@Slf4j
@Component
public class SessionInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        SessionContext sessionContext = SessionManager.getCurrentSession();
        if (sessionContext == null) {
            return "login";
        }
        return methodInvocation.proceed();
    }
}
