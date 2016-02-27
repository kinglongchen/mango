package com.kinglong.mango.admin.controller;

import com.kinglong.mango.biz.bizenum.Role;
import com.kinglong.mango.admin.domain.param.LoginParam;
import com.kinglong.mango.biz.domian.SessionContext;
import com.kinglong.mango.biz.manager.SessionManager;
import com.kinglong.mango.admin.tools.SessionTokenFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Controller
@RequestMapping("mango")
public class LoginController {

    @Resource
    SessionManager sessionManager;

    @Resource
    SessionTokenFactory sessionTokenFactory;
    @Value("${mango.user.name}")
    private String userName;

    @Value("${mango.user.password}")
    private String password;


    @RequestMapping("login/get")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "login/post",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE ,method = RequestMethod.POST)
    public String loginPost(HttpServletResponse response,LoginParam param) {
        String userName = param.getUserName();

        String password = param.getPassword();

        String sessionId;


        if (userName.equals(this.userName) && password.equals(this.password)) {
            sessionId = sessionTokenFactory.generate();
        } else {
            return "login";
        }
        SessionContext sessionContext = new SessionContext();
        sessionContext.setLoginTime(DateTime.now().toDate());
        sessionContext.setUserName(userName);
        sessionContext.setRole(Role.ADMIN);
        sessionManager.setSession(sessionId,sessionContext);
        Cookie cookie = new Cookie("sessionId",sessionId);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/mango/index";
    }


}
