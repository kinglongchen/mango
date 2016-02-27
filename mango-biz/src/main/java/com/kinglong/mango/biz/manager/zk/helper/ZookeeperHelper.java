package com.kinglong.mango.biz.manager.zk.helper;

import com.kinglong.mango.biz.common.constant.ZnodePathConstant;
import com.kinglong.mango.biz.domian.bo.StatZnodeInfoBO;
import com.kinglong.mango.biz.domian.bo.ZnodeBO;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by chenjinlong on 16/2/24.
 */
@Component
public class ZookeeperHelper {
    @Resource
    ZkClient zkClient;

    public StatZnodeInfoBO getStatZnodeInfo() {
        StatZnodeInfoBO statZnodeInfoBO = new StatZnodeInfoBO();
        //获取App相关统计信息
        Set<ZnodeBO> appZnodeSet = getChildrenByParent(ZnodePathConstant.CONFIG_ZNODE_PATH);
        statZnodeInfoBO.setAppZnodeSet(appZnodeSet);
        //获取Class相关统计信息
        Set<ZnodeBO> classZnodeSet = getChildrenByParentSet(appZnodeSet);
        statZnodeInfoBO.setClassZnodeSet(classZnodeSet);
        //获取Field相关统计信息
        Set<ZnodeBO> fieldZnodeSet = getChildrenByParentSet(classZnodeSet);
        statZnodeInfoBO.setFieldZnodeSet(fieldZnodeSet);
        //获取host相关统计信息
        Set<ZnodeBO> hostZnodeSet = getChildrenByParentSet(fieldZnodeSet);
        Set<ZnodeBO> filterHostZnodeSet = new HashSet<ZnodeBO>();
        Set<String> hostSet = new HashSet<String>();
        for (ZnodeBO hostZnode : hostZnodeSet) {
            if (! hostSet.contains(hostZnode.getName())) {
                hostSet.add(hostZnode.getName());
                filterHostZnodeSet.add(hostZnode);
            }
        }
        statZnodeInfoBO.setHostZnodeSet(filterHostZnodeSet);
        return statZnodeInfoBO;

    }

    private Set<ZnodeBO> getChildrenByParentSet(Set<ZnodeBO> parentZnodeSet) {
        Set<ZnodeBO> childrenZnodeSet = new HashSet<ZnodeBO>();
        if (parentZnodeSet == null) {
            return childrenZnodeSet;
        }

        for (ZnodeBO parentZnode : parentZnodeSet) {
            childrenZnodeSet.addAll(getChildrenByParent(parentZnode));
        }
        return childrenZnodeSet;
    }

    private Set<ZnodeBO> getChildrenByParent(ZnodeBO parentZnodeBO) {
        if (parentZnodeBO == null || parentZnodeBO.getPath() == null) {
            return new HashSet<ZnodeBO>();
        }
        return getChildrenByParent(parentZnodeBO.getPath());
    }

    private Set<ZnodeBO> getChildrenByParent(String parentPath) {
        Set<ZnodeBO> childrenSet = new HashSet<ZnodeBO>();
        List<String> childrenList = zkClient.getChildren(parentPath);
        if (childrenList == null || childrenList.size() == 0) {
            return childrenSet;
        }
        for (String children : childrenList) {
            ZnodeBO znodeBO = new ZnodeBO();
            znodeBO.setName(children);
            znodeBO.setPath(parentPath + "/" + children);
            znodeBO.setParentPath(parentPath);
            childrenSet.add(znodeBO);
        }
        return childrenSet;
    }
}
