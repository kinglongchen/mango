package com.kinglong.mango.common.node;

import com.kinglong.mango.common.base.AbstractMango;
import com.kinglong.mango.common.path.Path;
import com.kinglong.mango.common.path.ZKPath;
import com.kinglong.mango.register.Register;
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

    public <T> T getZkValue(Register register) {
        return register.readData(this.path.value());
    }

    public <T> void setZkValue(Register register, T data) {
        register.writeData(this.getPath().value(), data);
    }

    public Boolean isExists(Register register) {
        return register.exists(this.getPath().value());
    }


    public void create(Register register) {
        if (parent != null) {
            parent.create(register);
        }
        if (this.isExists(register)) {
            return;
        }
        String path = this.getPath().value();
        log.info("Starting Create ZKNode with path:" + path);
        register.create(this.getPath().value());
    }
}
