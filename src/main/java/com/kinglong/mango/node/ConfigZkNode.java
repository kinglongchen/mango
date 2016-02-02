package com.kinglong.mango.node;

import com.kinglong.mango.common.node.DefaultZKNode;

/**
 * -----------mango中的节点-----------------------zookeeper对应的节点--------------
 * ConfigNode 配置对应节点;<========================>/config/
 *
 * Created by chenjinlong on 15/7/9.
 */
public class ConfigZkNode extends DefaultZKNode {

    public ConfigZkNode(MangoZkNode rootNode) {
        super(rootNode, "config");
    }
}
