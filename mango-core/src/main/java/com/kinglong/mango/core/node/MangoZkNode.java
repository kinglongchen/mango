package com.kinglong.mango.core.node;

import com.kinglong.mango.core.common.node.DefaultZKNode;
import com.kinglong.mango.core.common.node.ZkNode;

/**
 * -----------mango中的节点-----------------------zookeeper对应的节点--------------
 * MangoNode mango对应的节点;<======================>/config/mango/
 * Created by chenjinlong on 15/7/9.
 */
public class MangoZkNode extends DefaultZKNode {

    public MangoZkNode(ZkNode parent) {
        super(parent, "mango");
    }

}
