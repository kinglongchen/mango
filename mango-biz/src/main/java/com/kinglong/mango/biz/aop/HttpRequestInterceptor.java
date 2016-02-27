package com.kinglong.mango.biz.aop;

import com.alibaba.fastjson.JSONArray;
import com.kinglong.mango.common.error.MangoCommonError;
import com.kinglong.mango.common.exception.MangoException;
import com.kinglong.mango.common.result.BaseResult;
import com.kinglong.mango.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by chenjinlong on 16/2/23.
 */
@Component
@Slf4j
public class HttpRequestInterceptor implements MethodInterceptor {
    public BaseResult invoke(MethodInvocation methodInvocation) throws Throwable {
        BaseResult result = new BaseResult();
        Object[] args = methodInvocation.getArguments();
        Method method = methodInvocation.getMethod();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        try {
            result = (BaseResult) methodInvocation.proceed();
        } catch (MangoException e) {
            log.error("接口请求错误[method=" + methodName + "] params=" + JSONArray.toJSONString(args) + "异常:", e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            result.setCode(e.getErrorCode());

        } catch (RuntimeException e) {
            log.error("接口请求错误[method="+methodName+"] params="+ JSONArray.toJSONString(args)+"异常:",e);
            result.setSuccess(false);
            result.setCode(MangoCommonError.MANGO_SYSTEM_ERROR.getCode());
            result.setMessage(MangoCommonError.MANGO_SYSTEM_ERROR.getMsg());
        }
        return result;
    }



}
