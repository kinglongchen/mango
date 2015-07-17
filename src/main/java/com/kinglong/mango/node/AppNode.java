package com.kinglong.mango.node;

import com.kinglong.mango.common.node.Node;
import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.zkclient.ZkConfigClient;

/**
 * Created by chenjinlong on 15/7/13.
 */
public class AppNode extends ZKNode {
    private static AppNode appNode = null;

    private AppNode(ZkConfigClient zkConfigClient, Node parent, String appName) {
        super(zkConfigClient, parent, appName);
        create();
    }

    public static AppNode factory(ZkConfigClient zkConfigClient, MangoNode parent, String appName) {
        if (AppNode.appNode == null) {
            AppNode.appNode = new AppNode(zkConfigClient, parent, appName);
        }
        return AppNode.appNode;
    }

    public static AppNode factory(MangoNode parent, String appName) {
        return AppNode.factory(parent.getZkConfigClient(), parent, appName);
    }
}
