package com.kinglong.mango.common.node;

import com.kinglong.mango.common.base.AbstractMango;
import com.kinglong.mango.common.path.Path;
import com.kinglong.mango.common.path.ZKPath;
import com.kinglong.mango.exception.MangoNullFieldException;
import com.kinglong.mango.zkclient.MangoZkClient;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * Created by chenjinlong on 15/7/8.
 */
@Slf4j(topic = "ZKNode")
public abstract class DefaultZKNode extends AbstractMango implements ZkNode {
    private Path path;
    private ZkNode parent;

    public DefaultZKNode() {
        this(null, "/");
    }

    public DefaultZKNode(ZkNode parent, Class clazz) {
        this(parent, clazz.getName());
    }

    public DefaultZKNode(ZkNode parent, Field field) {
        this(parent, field.getName());
    }

    public DefaultZKNode(ZkNode parent, String nodeName) {
        this.parent = parent;
        initPath(parent, nodeName);

    }

    private void initPath(Node parent, String pathName) {
        this.path = new ZKPath();
        if (parent == null) {
            this.path.value(null, pathName);
        } else {
            this.path.value(parent.getPath(), pathName);
        }
    }

    public ZkNode getParent() {
        return this.parent;
    }

    public Path getPath() {
        return this.path;
    }

    public <T> T getZkValue(MangoZkClient mangoZkClient) {
        return mangoZkClient.readData(this.path.value());
    }

    public <T> void setZkValue(MangoZkClient mangoZkClient, T data) {
        mangoZkClient.writeData(this.getPath().value(), data);
    }

    public Boolean isExists(MangoZkClient mangoZkClient) {
        return mangoZkClient.exists(this.getPath().value());
    }


    public void create(MangoZkClient mangoZkClient) {
        if (parent != null) {
            parent.create(mangoZkClient);
        }
        if (this.isExists(mangoZkClient)) {
            return;
        }
        String path = this.getPath().value();
        log.info("Starting Create ZKNode with path:" + path);
        mangoZkClient.createPersistent(this.getPath().value());
    }
}
