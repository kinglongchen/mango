package com.kinglong.mango.common.node;

import com.kinglong.mango.common.base.AbstractMango;
import com.kinglong.mango.common.path.Path;
import com.kinglong.mango.common.path.ZKPath;
import com.kinglong.mango.zkclient.ZkConfigClient;

import java.lang.reflect.Field;

/**
 * Created by chenjinlong on 15/7/8.
 */
public abstract class ZKNode extends AbstractMango implements Node {
    private Path path;
    private Node parent;
    private ZkConfigClient zkConfigClient;

    public ZKNode(ZkConfigClient zkConfigClient) {
        this(zkConfigClient, null, "/");
    }

    public ZKNode(ZkConfigClient zkConfigClient, Node parent, Class clazz) {
        this(zkConfigClient, parent, clazz.getName());
    }

    public ZKNode(ZkConfigClient zkConfigClient, Node parent, Field field) {
        this(zkConfigClient, parent, field.getName());
    }

    public ZKNode(ZkConfigClient zkConfigClient, Node parent, String nodeName) {
        this.parent = parent;
        initPath(parent, nodeName);
        this.zkConfigClient = zkConfigClient;

    }

    private void initPath(Node parent, String pathName) {
        this.path = new ZKPath();
        if (parent == null) {
            this.path.value(null, pathName);
        } else {
            this.path.value(parent.getPath(), pathName);
        }
    }

    public Node getParent() {
        return this.parent;
    }

    public Path getPath() {
        return this.path;
    }

    public ZkConfigClient getZkConfigClient() {
        return this.zkConfigClient;
    }

    public <T> T getZkValue(ZkConfigClient zkConfigClient) {
        T data = zkConfigClient.readData(this.getPath().value());
        return data;
    }

    public <T> T getZkValue() {
        return getZkValue(zkConfigClient);
    }


    public <T> void setZkValue(T data) {
        this.setZkValue(zkConfigClient, data);
    }

    public <T> void setZkValue(ZkConfigClient zkConfigClient, T data) {
        zkConfigClient.writeData(this.getPath().value(), data);
    }

    public Boolean isExists(ZkConfigClient zkConfigClient) {
        return zkConfigClient.exists(this.getPath().value());
    }

    public Boolean isExists() {
        return isExists(zkConfigClient);
    }

    public void create(ZkConfigClient zkConfigClient) {
        if (this.isExists(zkConfigClient)) {
            return;
        }
        zkConfigClient.createPersistent(this.getPath().value());
    }

    public void create() {
        create(zkConfigClient);
    }
}
