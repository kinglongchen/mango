package com.kinglong.mango.node;

import com.google.common.collect.Maps;
import com.kinglong.mango.common.node.DefaultZKNode;
import com.kinglong.mango.common.node.ZkNode;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * -----------mango中的节点-----------------------zookeeper对应的节点--------------
 * AppNode 应用程序对应的节点;<======================>/config/mango/{app.name}/
 * Created by chenjinlong on 15/7/13.
 */
@Slf4j
public class AppZkNode extends DefaultZKNode {
    private Map<String,ClassZkNode> classNodeMap;

    public AppZkNode(ZkNode parent, String appName) {
        super(parent, appName);
        classNodeMap = Maps.newHashMap();
    }
    public ClassZkNode register(Class clazz) {
        if (classNodeMap.keySet().contains(clazz.getName())) {
            String warnMsg = String.format("[MANGO] Duplicate register " +
                    "the class with name[%s],it will ignore the registration this time", clazz.getName());
            log.warn(warnMsg);
        }
        log.info(String.format("[MANGO]Register class [%s]",clazz.getName()));
        ClassZkNode classNode = new ClassZkNode(this,clazz);

        classNodeMap.put(clazz.getName(),classNode);

        return classNode;
    }
}
