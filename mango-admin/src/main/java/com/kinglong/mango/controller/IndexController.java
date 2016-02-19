package com.kinglong.mango.controller;

import com.kinglong.mango.annotation.SessionAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Controller
@RequestMapping("mango")
public class IndexController {
    @RequestMapping("index")
    @SessionAuth
    public String index() {
        return "index";
    }
}
