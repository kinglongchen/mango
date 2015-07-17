package com.kinglong.mango.node;

import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.zkclient.ZkConfigClient;

/**
 * Created by chenjinlong on 15/7/9.
 */
public class RootNode extends ZKNode {
    private static RootNode rootNode = null;
    private RootNode(ZkConfigClient zkConfigClient) {
        super(zkConfigClient);
        create();
    }
    public synchronized static RootNode factory(ZkConfigClient zkConfigClient) {
        if (RootNode.rootNode == null) {
            RootNode.rootNode = new RootNode(zkConfigClient);
        }
        return RootNode.rootNode;
    }
}
