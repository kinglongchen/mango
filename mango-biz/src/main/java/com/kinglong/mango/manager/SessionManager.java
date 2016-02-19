package com.kinglong.mango.manager;

import com.kinglong.mango.common.util.StringUtil;
import com.kinglong.mango.domian.SessionContext;
import com.kinglong.mango.exception.MangoException;
import org.springframework.stereotype.Service;

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

    public SessionContext deleteSession(String sessionId) {
        return sessionContextMap.remove(sessionId);
    }


}
