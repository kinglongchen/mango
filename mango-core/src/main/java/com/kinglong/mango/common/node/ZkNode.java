package com.kinglong.mango.common.node;

import com.kinglong.mango.zkclient.MangoZkClient;

/**
 * Created by chenjinlong on 16/1/31.
 */
public interface ZkNode extends Node{

    <T> T getZkValue(MangoZkClient mangoZkClient);

    <T> void setZkValue(MangoZkClient mangoZkClient, T data);

    void create(MangoZkClient mangoZkClient);

    Boolean isExists(MangoZkClient mangoZkClient);

    ZkNode getParent();
}
