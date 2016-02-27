package com.kinglong.mango.biz.manager.zk;

import com.kinglong.mango.biz.domian.bo.StatInfoBO;
import com.kinglong.mango.biz.domian.bo.StatZnodeInfoBO;
import com.kinglong.mango.biz.manager.zk.helper.ZookeeperHelper;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by chenjinlong on 16/2/23.
 */

@Service
public class ZookeeperManager {
    @Resource
    ZkClient zkClient;

    @Resource
    ZookeeperHelper zookeeperHelper;


    public StatInfoBO getStatInfo() {
        StatInfoBO statInfoBO = new StatInfoBO();
        StatZnodeInfoBO statZnodeInfoBO = zookeeperHelper.getStatZnodeInfo();
        statInfoBO.setAppNumber(statZnodeInfoBO.getAppZnodeSet().size());
        statInfoBO.setClassNumber(statZnodeInfoBO.getClassZnodeSet().size());
        statInfoBO.setFieldNumber(statZnodeInfoBO.getFieldZnodeSet().size());

        statInfoBO.setHostNumber(statZnodeInfoBO.getHostZnodeSet().size());
        return statInfoBO;
    }
}
