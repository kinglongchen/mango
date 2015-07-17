package com.kinglong.mango.common.node;

import com.kinglong.mango.common.base.Mango;
import com.kinglong.mango.common.path.Path;
import com.kinglong.mango.zkclient.ZkConfigClient;

/**
 * Created by chenjinlong on 15/7/8.
 */
public interface Node extends Mango {
    Path getPath();

    <T> T getZkValue(ZkConfigClient zkConfigClient);

    <T> void setZkValue(ZkConfigClient zkConfigClient, T data);

    void create(ZkConfigClient zkConfigClient);

    Boolean isExists(ZkConfigClient zkConfigClient);
}
