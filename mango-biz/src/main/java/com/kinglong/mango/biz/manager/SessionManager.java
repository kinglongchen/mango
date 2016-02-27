package com.kinglong.mango.biz.manager;

import com.kinglong.mango.biz.domian.SessionContext;
import com.kinglong.mango.common.exception.MangoException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.kinglong.mango.common.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Service
public class SessionManager {

    private final static Integer MAX_SESSION_NUM = 10;
    private static Map<String,SessionContext> sessionContextMap = new HashMap<String, SessionContext>();

    public void setSession(String sessionId,SessionContext sessionContext) {
        if (sessionContextMap.size()>10) {
            throw new MangoException("登陆用户已达到最大数目!");
        }
        if (StringUtil.isEmptyAfterTrim(sessionId)) {
            throw new MangoException("系统内部错误,请联系管理员!");
        }
        sessionContextMap.put(sessionId,sessionContext);
    }

    public SessionContext getSession(String sessionId) {
        return sessionContextMap.get(sessionId);
    }

    public static SessionContext getCurrentSession() {
        HttpServletRequest request = getRequest();
        Cookie[] cookies = request.getCookies();
        String sessionId = null;
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("sessionId")) {
                sessionId = cookie.getValue();
            }
        }

        return sessionContextMap.get(sessionId);
    }

    public SessionContext deleteSession(String sessionId) {
        return sessionContextMap.remove(sessionId);
    }

    private static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }

}
