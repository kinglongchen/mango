package com.kinglong.mango.node;

import com.kinglong.mango.common.node.ZKNode;
import com.kinglong.mango.zkclient.ZkConfigClient;
import com.sun.tools.hat.internal.model.Root;

/**
 * Created by chenjinlong on 15/7/9.
 */
public class ConfigNode extends ZKNode {
    private static ConfigNode configNode = null;

    private ConfigNode(ZkConfigClient zkConfigClient, RootNode rootNode) {
        super(zkConfigClient, rootNode, "config");
        create();
    }

    public static ConfigNode factory(ZkConfigClient zkConfigClient, RootNode rootNode) {
        if (ConfigNode.configNode == null) {
            ConfigNode.configNode = new ConfigNode(zkConfigClient, rootNode);
        }

        return ConfigNode.configNode;
    }

    public static ConfigNode factory(RootNode parent) {
        return ConfigNode.factory(parent.getZkConfigClient(), parent);
    }
}
