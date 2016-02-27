package com.kinglong.mango.admin.controller;

import com.kinglong.mango.admin.domain.vo.StatInfoVO;
import com.kinglong.mango.biz.manager.zk.ZookeeperManager;
import com.kinglong.mango.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kinglong.mango.common.util.BeanUtil;

import javax.annotation.Resource;

/**
 * Created by chenjinlong on 16/2/23.
 */
@Controller
@RequestMapping("mango")
public class DashboardController {
    @Resource
    ZookeeperManager zookeeperManager;

    @RequestMapping(value = "info/stat")
    @ResponseBody
    public Result<StatInfoVO> getStatInfo() {
        StatInfoVO statInfoVO = BeanUtil.objTran(zookeeperManager.getStatInfo(),StatInfoVO.class);
        return Result.wrapSuccessfulResult(statInfoVO);
    }

}
