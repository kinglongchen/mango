package com.kinglong.mango.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kinglong.mango.admin.adminenum.MenuEnum;
import com.kinglong.mango.admin.annotation.SessionAuth;
import com.kinglong.mango.biz.auth.RoleAuth;
import com.kinglong.mango.biz.auth.menu.Menu;
import com.kinglong.mango.biz.domian.SessionContext;
import com.kinglong.mango.biz.domian.bo.StatInfoBO;
import com.kinglong.mango.biz.manager.SessionManager;
import com.kinglong.mango.biz.manager.zk.ZookeeperManager;
import com.kinglong.mango.common.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenjinlong on 16/2/5.
 */
@Controller
@RequestMapping("mango")
public class IndexController {
    @Resource
    ZookeeperManager zookeeperManager;
    @RequestMapping("{selectMenu}")
    @SessionAuth
    public String index(ModelMap modelMap,@PathVariable String selectMenu) {
        SessionContext sessionContext = SessionManager.getCurrentSession();
        List<Menu> menus = RoleAuth.getMenusByRole(sessionContext.getRole());

        modelMap.addAttribute("sideMenus",menus);
        modelMap.addAttribute("initData",getMenuInitData(selectMenu));
        modelMap.addAttribute("selectMenu", selectMenu);

        return "framework/main";
    }

    private Map getMenuInitData(String menuName) {
        switch (MenuEnum.getMenuByName(menuName)) {
            case DASHBOARD:
                return getDashBoardInitData();
            case OTHER:
            default:
                return new HashMap();
        }

    }

    private Map getDashBoardInitData() {
        StatInfoBO statInfoBO = zookeeperManager.getStatInfo();
        return (JSONObject)JSON.toJSON(statInfoBO);
    }


}
