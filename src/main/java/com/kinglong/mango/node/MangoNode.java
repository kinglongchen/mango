package com.kinglong.mango.node;

import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.common.node.Node;
import com.kinglong.mango.zkclient.ZkConfigClient;

/**
 * Created by chenjinlong on 15/7/9.
 */
public class MangoNode extends ZKNode {
    private static MangoNode mangoNode = null;

    private MangoNode(ZkConfigClient zkConfigClient, Node parent) {
        super(zkConfigClient, parent, "mango");
        create();
    }

    public static MangoNode factory(ZkConfigClient zkConfigClient, ConfigNode parent) {
        if (MangoNode.mangoNode == null) {
            MangoNode.mangoNode = new MangoNode(zkConfigClient, parent);
        }
        return MangoNode.mangoNode;
    }

    public static MangoNode factory(ConfigNode parent) {
        return MangoNode.factory(parent.getZkConfigClient(), parent);
    }

}
